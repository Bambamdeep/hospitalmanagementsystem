package com.hospitalmanagement.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hospitalmanagement.appointment.dto.AppointmentStatus;
import com.hospitalmanagement.appointment.exception.AppointmentNotFoundException;
import com.hospitalmanagement.appointment.model.Appointment;
import com.hospitalmanagement.appointment.repository.AppointmentRepository;
import com.hospitalmanagement.appointment.service.AppointmentServiceImpl;
import com.hospitalmanagement.appointment.service.DoctorClient;
import com.hospitalmanagement.appointment.service.PatientClient;

public class AppointmentServiceTest {
	@Mock
	private AppointmentRepository appointmentRepository;

	@Mock
	private PatientClient patientClient;

	@Mock
	private DoctorClient doctorClient;

	@InjectMocks
	private AppointmentServiceImpl appointmentService;

	private Appointment sampleAppointment;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		sampleAppointment = new Appointment();
		sampleAppointment.setAppointmentId(1L);
		sampleAppointment.setPatientId(101L);
		sampleAppointment.setDoctorId(202L);
		sampleAppointment.setAppointmentDateTime(LocalDateTime.now().plusDays(1));
		sampleAppointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
	}

	@Test
	public void testScheduleAppointment() {
		when(appointmentRepository.save(sampleAppointment)).thenReturn(sampleAppointment);
		Appointment created = appointmentService.scheduleAppointment(sampleAppointment);
		assertNotNull(created.getAppointmentId());
		assertEquals(AppointmentStatus.SCHEDULED, created.getAppointmentStatus());
		verify(appointmentRepository, times(1)).save(sampleAppointment);
	}

	@Test
	public void testConfirmAppointment_Success() throws AppointmentNotFoundException {
		when(appointmentRepository.findById(1L)).thenReturn(Optional.of(sampleAppointment));
		sampleAppointment.setAppointmentStatus(AppointmentStatus.CONFIRM);
		when(appointmentRepository.save(sampleAppointment)).thenReturn(sampleAppointment);

		Appointment confirmed = appointmentService.confirmAppointment(1L);
		assertEquals(AppointmentStatus.CONFIRM, confirmed.getAppointmentStatus());
	}

	@Test
	public void testConfirmAppointment_NotFound() {
		when(appointmentRepository.findById(2L)).thenReturn(Optional.empty());
		assertThrows(AppointmentNotFoundException.class, () -> appointmentService.confirmAppointment(2L));
	}

	@Test
	public void testUpdateAppointment_Success() {
		Appointment updated = new Appointment();
		updated.setPatientId(102L);
		updated.setDoctorId(203L);
		updated.setAppointmentDateTime(LocalDateTime.now().plusDays(2));
		updated.setAppointmentStatus(AppointmentStatus.CONFIRM);

		when(appointmentRepository.findById(1L)).thenReturn(Optional.of(sampleAppointment));
		when(appointmentRepository.save(any(Appointment.class))).thenReturn(updated);

		Appointment result = appointmentService.updateAppointment(updated, 1L);
		assertEquals(102L, result.getPatientId());
		assertEquals(AppointmentStatus.CONFIRM, result.getAppointmentStatus());
	}

	@Test
	public void testCancelAppointment_Success() {
		sampleAppointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
		when(appointmentRepository.findById(1L)).thenReturn(Optional.of(sampleAppointment));
		doNothing().when(appointmentRepository).deleteById(1L);

		Appointment cancelled = appointmentService.cancelAppointment(1L);
		assertEquals(AppointmentStatus.CANCELLED, cancelled.getAppointmentStatus());
		verify(appointmentRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testCancelAppointment_NotFound() {
		when(appointmentRepository.findById(2L)).thenReturn(Optional.empty());
		assertThrows(AppointmentNotFoundException.class, () -> appointmentService.cancelAppointment(2L));
	}
}

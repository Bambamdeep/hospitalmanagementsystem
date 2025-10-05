package com.hospitalmanagement.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hospitalmanagement.appointment.dto.AppointmentStatus;
import com.hospitalmanagement.appointment.model.Appointment;
import com.hospitalmanagement.appointment.service.AppointmentService;
import com.hospitalmanagement.appointment.service.DoctorClient;
import com.hospitalmanagement.appointment.service.PatientClient;

@SpringBootTest
public class AppointmentTest {
	@Autowired
	private AppointmentService appointmentService;

	private Appointment createSampleAppointment() {
		Appointment appointment = new Appointment();
		appointment.setPatientId(101L);
		appointment.setDoctorId(202L);
		appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(1));
		appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
		return appointmentService.scheduleAppointment(appointment);
	}

	@Test
	public void testCreateAppointment() {
		Appointment appointment = createSampleAppointment();
		assertNotNull(appointment.getAppointmentId());
		assertEquals(101L, appointment.getPatientId());
		assertEquals(202L, appointment.getDoctorId());
		assertEquals(AppointmentStatus.SCHEDULED, appointment.getAppointmentStatus());
		assertNotNull(appointment.getScheduleAt());
	}

}

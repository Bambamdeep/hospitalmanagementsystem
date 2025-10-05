package com.example.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.doctor.exception.DoctorIdNotFoundException;
import com.example.doctor.exception.DoctorNotFoundException;
import com.example.doctor.model.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.service.DoctorServiceImpl;


public class DoctorTestService {
	  @Mock
	    private DoctorRepository doctorRepository;

	    @InjectMocks
	    private DoctorServiceImpl doctorService;

	    private Doctor doctor;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        doctor = new Doctor();
	        doctor.setDoctorId(1L);
	        doctor.setName("Dr. Kumar");
	        doctor.setSpecilization("Cardiology");
	        doctor.setDepartmetId(101L);
	        doctor.setPhoneNumber("+911234567890");
	    }

	    @Test
	    void testCreateDoctor() {
	        when(doctorRepository.save(doctor)).thenReturn(doctor);
	        Doctor result = doctorService.createDoctor(doctor);
	        assertNotNull(result);
	        assertEquals("Dr. Kumar", result.getName());
	    }

	    @Test
	    void testGetAllDoctor() {
	        when(doctorRepository.findAll()).thenReturn(List.of(doctor));
	        List<Doctor> result = doctorService.getAllDoctor();
	        assertEquals(1, result.size());
	    }

	    @Test
	    void testGetDoctorById_Found() {
	        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
	        Doctor result = doctorService.getDoctorById(1L);
	        assertEquals(1L, result.getDoctorId());
	    }

	    @Test
	    void testGetDoctorById_NotFound() {
	        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());
	        assertThrows(DoctorIdNotFoundException.class, () -> doctorService.getDoctorById(1L));
	    }

	    @Test
	    void testUpdateDoctor() {
	        Doctor updated = new Doctor();
	        updated.setName("Dr. Deepak");
	        updated.setSpecilization("Neurology");
	        updated.setDepartmetId(102L);
	        updated.setPhoneNumber("+919876543210");

	        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
	        when(doctorRepository.save(any(Doctor.class))).thenReturn(updated);

	        Doctor result = doctorService.updateDoctor(updated, 1L);
	        assertEquals("Dr. Deepak", result.getName());
	        assertEquals("Neurology", result.getSpecilization());
	    }

	    @Test
	    void testDeleteDoctor_Success() {
	        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
	        boolean result = doctorService.deleteDoctor(1L);
	        assertTrue(result);
	        verify(doctorRepository).delete(doctor);
	    }

	    @Test
	    void testDeleteDoctor_NotFound() {
	        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());
	        assertThrows(DoctorIdNotFoundException.class, () -> doctorService.deleteDoctor(1L));
	    }

	    @Test
	    void testFindBySpecilization_Found() {
	        when(doctorRepository.findBySpecilization("Cardiology")).thenReturn(List.of(doctor));
	        List<Doctor> result = doctorService.findBySpecilization("Cardiology");
	        assertFalse(result.isEmpty());
	    }

	    @Test
	    void testFindBySpecilization_NotFound() {
	        when(doctorRepository.findBySpecilization("Oncology")).thenReturn(List.of());
	        assertThrows(DoctorNotFoundException.class, () -> doctorService.findBySpecilization("Oncology"));
	    }

	    @Test
	    void testFindByDepartmetId_Found() {
	        when(doctorRepository.findByDepartmetId(101L)).thenReturn(List.of(doctor));
	        List<Doctor> result = doctorService.findByDepartmetId(101L);
	        assertFalse(result.isEmpty());
	    }

	    @Test
	    void testFindByDepartmetId_NotFound() {
	        when(doctorRepository.findByDepartmetId(999L)).thenReturn(List.of());
	        assertThrows(DoctorNotFoundException.class, () -> doctorService.findByDepartmetId(999L));
	    }
}

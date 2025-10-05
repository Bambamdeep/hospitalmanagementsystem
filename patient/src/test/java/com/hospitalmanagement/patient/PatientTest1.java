package com.hospitalmanagement.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import com.hospitalmanagement.patient.exception.PatientNotFoundException;
import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.repository.PatientRepository;
import com.hospitalmanagement.patient.service.PatientServiceImpl;

public class PatientTest1 {
	   @Mock
	    private PatientRepository patientRepository;

	    @InjectMocks
	    private PatientServiceImpl patientService;

	    private Patient patient;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        patient = new Patient();
	        patient.setPatientId(1L);
	        patient.setName("John Doe");
	        patient.setAge(30);
	        patient.setAddress("123 Street");
	        patient.setGender("Male");
	        patient.setPhoneNumber("1234567890");
	    }

	    @Test
	    void testCreatePatient() {
	        when(patientRepository.save(patient)).thenReturn(patient);
	        Patient result = patientService.createPatient(patient);
	        assertEquals("John Doe", result.getName());
	    }
	    @Test
	    void testGetAllPatient() {
	        when(patientRepository.findAll()).thenReturn(List.of(patient));
	        List<Patient> result = patientService.getAllPatient();
	        assertEquals(1, result.size());
	    }

	    @Test
	      void testGetPatientById_Found() {
	          when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
	          Patient result = patientService.getPatientById(1L);
	          assertEquals(1L, result.getPatientId());
	      }

	

	    @Test
	    void testGetPatientById_NotFound() {
	        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
	        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(1L));
	    }

	    @Test
	    void testDeletePatient_Success() {
	        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
	        boolean result = patientService.deletePatient(1L);
	        assertTrue(result);
	        verify(patientRepository).delete(patient);
	    }

	    @Test
	    void testDeletePatient_NotFound() {
	        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
	        assertThrows(PatientNotFoundException.class, () -> patientService.deletePatient(1L));
	    }

	    @Test
	    void testUpdateCustomer() {
	        Patient updated = new Patient();
	        updated.setName("Jane Doe");
	        updated.setAge(28);
	        updated.setAddress("456 Avenue");
	        updated.setGender("Female");
	        updated.setPhoneNumber("9876543210");

	        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
	        when(patientRepository.save(any(Patient.class))).thenReturn(updated);

	        Patient result = patientService.updateCustomer(updated, 1L);
	        assertEquals("Jane Doe", result.getName());
	    }

	    @Test
	    void testFindByName_Found() {
	        when(patientRepository.findAll()).thenReturn(List.of(patient));
	        List<Patient> result = patientService.findByName("John Doe");
	        assertEquals(1, result.size());
	    }

	    @Test
	    void testFindByName_NotFound() {
	        when(patientRepository.findAll()).thenReturn(List.of());
	        assertThrows(PatientNotFoundException.class, () -> patientService.findByName("Unknown"));
	    }


}

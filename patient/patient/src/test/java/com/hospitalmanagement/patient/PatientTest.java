package com.hospitalmanagement.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.service.PatientService;

@SpringBootTest
public class PatientTest {
	@Autowired
	private PatientService patientService;

	private Patient createSamplePatient() {
		Patient patient = new Patient();
		patient.setName("Deepak");
		patient.setAge(30);
		patient.setAddress("Kolkata");
		patient.setGender("Male");
		patient.setPhoneNumber("+919876543210");
		return patientService.createPatient(patient);
	}

	@Test
	public void testCreatePatient() {

		Patient patient = createSamplePatient();
		assertNotNull(patient.getPatientId());
		assertEquals("Deepak", patient.getName());

	}

	@Test
	public void testGetAllPatient() {
		createSamplePatient();
		List<Patient> patients = patientService.getAllPatient();
		assertFalse(patients.isEmpty());
	}

	@Test
	public void testGetPatientById() {

		Patient savedPatient = createSamplePatient();
		Patient fetchedPatient = patientService.getPatientById(savedPatient.getPatientId());
		assertNotNull(fetchedPatient);
		assertEquals(savedPatient.getPatientId(), fetchedPatient.getPatientId());

	}

	public void testDeletePatient() {
		Patient savedPatient = createSamplePatient();
		boolean deleted = patientService.deletePatient(savedPatient.getPatientId());
		assertTrue(deleted);
	}

	@Test
	public void testFindByName() {
		createSamplePatient();
		List<Patient> patients = patientService.findByName("Deepak");
		assertFalse(patients.isEmpty());
		assertEquals("Deepak", patients.get(0).getName());
	}

	@Test
	public void testUpdateCustomer() {

		Patient patient = new Patient();
		patient.setName("Pratik");
		patient.setAge(28);
		patient.setAddress("Patna");
		patient.setGender("Male");
		patient.setPhoneNumber("+911234567890");

		Patient savedPatient = patientService.createPatient(patient);
		assertNotNull(savedPatient.getPatientId());

		savedPatient.setName("Deepak");
		savedPatient.setAge(35);

		Patient updatedPatient = patientService.updateCustomer(savedPatient, savedPatient.getPatientId());

		assertNotNull(updatedPatient);
		assertEquals("Deepak", updatedPatient.getName());
		assertEquals(35, updatedPatient.getAge());

	}
}

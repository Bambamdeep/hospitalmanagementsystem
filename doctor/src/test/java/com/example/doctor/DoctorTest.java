package com.example.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.doctor.model.Doctor;
import com.example.doctor.service.DoctorService;

@SpringBootTest
public class DoctorTest {
	@Autowired
	private DoctorService doctorService;

	private Doctor createSampleDoctor() {
		Doctor doctor = new Doctor();
		doctor.setName("Dr. Kumar");
		doctor.setSpecilization("Cardiology");
		doctor.setDepartmetId(101L);
		doctor.setPhoneNumber("+911234567890");
		return doctorService.createDoctor(doctor);
	}

	@Test
	public void testCreateDoctor() {
		Doctor doctor = createSampleDoctor();
		assertNotNull(doctor.getDoctorId());
		assertEquals("Dr. Kumar", doctor.getName());
	}

	@Test
	public void testGetAllDoctor() {
		createSampleDoctor();
		List<Doctor> doctors = doctorService.getAllDoctor();
		assertFalse(doctors.isEmpty());
	}

	@Test
	public void testGetDoctorById() {
		Doctor savedDoctor = createSampleDoctor();
		Doctor fetchedDoctor = doctorService.getDoctorById(savedDoctor.getDoctorId());
		assertNotNull(fetchedDoctor);
		assertEquals(savedDoctor.getDoctorId(), fetchedDoctor.getDoctorId());
	}

	@Test
	public void testDeleteDoctor() {
		Doctor savedDoctor = createSampleDoctor();
		boolean deleted = doctorService.deleteDoctor(savedDoctor.getDoctorId());
		assertTrue(deleted);
	}

	@Test
	public void testUpdateDoctor() {
		Doctor updated = new Doctor();
		updated.setName("Dr. Deepak");
		updated.setSpecilization("Orth");
		updated.setDepartmetId(102L);
		updated.setPhoneNumber("+919876543210");

		Doctor existingDoctor = new Doctor();
		existingDoctor.setDoctorId(1L);
		existingDoctor.setName("Dr. Pratik");
		existingDoctor.setSpecilization("Neurology");
		existingDoctor.setDepartmetId(101L);
		existingDoctor.setPhoneNumber("+911234567890");

		Doctor result = doctorService.updateDoctor(updated, 1L);

		assertNotNull(result);
		assertEquals("Dr. Deepak", result.getName());
		assertEquals("Orth", result.getSpecilization());

	}

	@Test
	public void testFindBySpecilization() {
		createSampleDoctor();
		List<Doctor> doctors = doctorService.findBySpecilization("Cardiology");
		assertFalse(doctors.isEmpty());
		assertEquals("Cardiology", doctors.get(0).getSpecilization());
	}

	@Test
	public void testFindByDepartmetId() {
		createSampleDoctor();
		List<Doctor> doctors = doctorService.findByDepartmetId(101L);
		assertFalse(doctors.isEmpty());
		assertEquals(101L, doctors.get(0).getDepartmetId());
	}

}

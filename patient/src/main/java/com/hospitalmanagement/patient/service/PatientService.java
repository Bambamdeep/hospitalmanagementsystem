package com.hospitalmanagement.patient.service;

import java.util.List;

import com.hospitalmanagement.patient.model.Patient;

public interface PatientService {
	public Patient createPatient(Patient patient);

	public List<Patient> getAllPatient();

	public Patient getPatientById(long patientId);

	public boolean deletePatient(long patientId);

	public Patient updateCustomer(Patient patient, long patientId);

	public List<Patient> findByName(String name);

}

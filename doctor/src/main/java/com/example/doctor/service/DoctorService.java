package com.example.doctor.service;

import java.util.List;

import com.example.doctor.model.Doctor;

public interface DoctorService {

	public Doctor createDoctor(Doctor doctor);

	public List<Doctor> getAllDoctor();

	public Doctor getDoctorById(long doctorId);

	public Doctor updateDoctor(Doctor doctor, long doctorId);

	public boolean deleteDoctor(long doctorId);

	public List<Doctor> findBySpecilization(String specilization);

	List<Doctor> findByDepartmetId(long departemtId);

}

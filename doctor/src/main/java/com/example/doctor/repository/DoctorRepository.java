package com.example.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doctor.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findBySpecilization(String pecilization);

	List<Doctor> findByDepartmetId(long departemtId);

}

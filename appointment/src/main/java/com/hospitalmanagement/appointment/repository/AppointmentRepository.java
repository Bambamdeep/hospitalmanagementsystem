package com.hospitalmanagement.appointment.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmanagement.appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Optional<Appointment> findByPatientId(long patientId);

	Appointment findByDoctorId(long doctorId);

}

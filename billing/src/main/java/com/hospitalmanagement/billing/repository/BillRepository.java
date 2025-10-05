package com.hospitalmanagement.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmanagement.billing.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

	Optional<Bill> findByPatientId(Long patientId);

	Optional<Bill> findByAppointmentId(Long appointmentId);

}

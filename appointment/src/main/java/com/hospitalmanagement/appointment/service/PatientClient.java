package com.hospitalmanagement.appointment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospitalmanagement.appointment.configuration.PatientClientConfiguration;
import com.hospitalmanagement.appointment.dto.Patient;

//@FeignClient(name="patient",configuration= PatientClientConfiguration.class)
@FeignClient(name="patient")
public interface PatientClient {
	@GetMapping("/patient/id/{patientId}")
	Patient getPatientById(@PathVariable (("patientId"))long patientId);
	

}

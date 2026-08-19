package com.hospitalmanagement.billing.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospitalmanagement.billing.configuration.PatientClientConfiguration1;

import com.hospitalmanagement.billing.utill.PatientDto;

//@FeignClient(name = "patient", configuration = PatientClientConfiguration1.class)
@FeignClient(name = "patient")
public interface PatientClient {
	@GetMapping("/patient/id/{patientId}")
	PatientDto getPatientById(@PathVariable(("patientId")) long patientId);

}

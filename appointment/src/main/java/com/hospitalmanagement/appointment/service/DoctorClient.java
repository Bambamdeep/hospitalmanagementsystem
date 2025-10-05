package com.hospitalmanagement.appointment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospitalmanagement.appointment.dto.Doctor;

@FeignClient(name="doctor")
public interface DoctorClient {
   @GetMapping("/doctor/id/{doctorId}")
	public Doctor getDoctorById(@PathVariable("doctorId") long doctorId);
	
	
}

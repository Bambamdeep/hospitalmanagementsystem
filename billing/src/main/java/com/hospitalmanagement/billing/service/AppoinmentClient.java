package com.hospitalmanagement.billing.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospitalmanagement.billing.utill.AppointmentDto;

@FeignClient(name = "appointment")
public interface AppoinmentClient {
	
	@GetMapping("/appointment/id/{appointmentId}")
 AppointmentDto getAppointmentId(@PathVariable("appointmentId") long appointmentId) ;

	
	
	
	
}

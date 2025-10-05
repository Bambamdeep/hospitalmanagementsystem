package com.hospitalmanagement.appointment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospitalmanagement.appointment.dto.Patient;
import com.hospitalmanagement.appointment.exception.PatientServiceUnavailableException;

public class PatientServiceFallback implements PatientClient{
	private static final Logger log = LoggerFactory.getLogger(PatientServiceFallback.class);
	@Override
	public Patient getPatientById(long patientId) {
		log.error("Patient Service is Under maintaince for Id{}",patientId);
	 throw new PatientServiceUnavailableException("Patient service is currently maintaince");
	}

}

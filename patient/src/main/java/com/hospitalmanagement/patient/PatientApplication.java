package com.hospitalmanagement.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.service.PatientService;

@SpringBootApplication
@EnableDiscoveryClient	
public class PatientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
@Autowired
private PatientService patientService;
	public PatientApplication(PatientService patientService) {
	super();
	this.patientService = patientService;
}
	@Override
	public void run(String... args) throws Exception {
	Patient patient = new Patient(18,"Kumar",15,"MALE","Kolkata","+916206079081");
	
		patientService.createPatient(patient);
	}

}

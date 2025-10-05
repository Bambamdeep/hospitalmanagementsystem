package com.example.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.doctor.model.Doctor;
import com.example.doctor.service.DoctorService;

@SpringBootApplication
@EnableDiscoveryClient
public class DoctorApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DoctorApplication.class, args);
	}
  @Autowired
  private DoctorService doctorService;
  
  
	public DoctorApplication(DoctorService doctorService) {
	super();
	this.doctorService = doctorService;
}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Doctor doctor = new Doctor(10,"Pratik","physician",22,"+916206079081");
		//doctorService.createDoctor(doctor);
	}

}

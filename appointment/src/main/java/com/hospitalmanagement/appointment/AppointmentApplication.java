package com.hospitalmanagement.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.hospitalmanagement.appointment.service.AppointmentService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.hospitalmanagement.appointment.service")
public class AppointmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Autowired
private AppointmentService appointmentService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// LocalDateTime dateTime = LocalDateTime.parse("2025-10-27T15:30:00");
		// Appointment appointmet = new Appointment(1,1,10,dateTime);
		// appointmentService.scheduleAppointment(appointmet);

	}

}

package com.hospitalmanagement.patient.configuration;


import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hospitalmanagement.patient.dto.AppointmentNotificationDto;
import com.hospitalmanagement.patient.exception.PatientNotFoundException;
import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.repository.PatientRepository;

@Configuration
public class PatientConsumer {

	@Autowired
	private PatientRepository patientRepository;

	@Bean
	public Function<AppointmentNotificationDto, String> appointmentCreated() {
		return patientNotication -> {

			// Optional<Patient> optionalPatient =
			// patientRepository.findById(patientNotication.getPatientId());
			Patient idByPatient = patientRepository.findById(patientNotication.getPatientId())
					.orElseThrow(() -> new PatientNotFoundException("Patient not found with this Id"));
			if (idByPatient != null) {

				String message = buildNotificationMessage(idByPatient, patientNotication);
				sendAlertToPatient(idByPatient.getPhoneNumber(), message);
				return "Notification sent to patient: " + idByPatient.getName();
			} else {
				System.out.println("No patient found for ID: " + patientNotication.getPatientId());
				return "Patient not found";
			}

		};
	}

	private void sendAlertToPatient(String phoneNumber, String message) {
		System.out.println("Sending alert to " + phoneNumber);
		System.out.println("Message: " + message);
		System.out.println("Alert sent successfully");
		System.out.println("_________________________");
	}

	private String buildNotificationMessage(Patient patient, AppointmentNotificationDto appointmentNotificationDto) {
		return String.format("Hello %s, your appointment (ID: %d) is scheduled for %s.", patient.getName(),
				appointmentNotificationDto.getAppointmentId(), appointmentNotificationDto.getAppointmentDateTime());
	}

}

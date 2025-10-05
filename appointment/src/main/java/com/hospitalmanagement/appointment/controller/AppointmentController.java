package com.hospitalmanagement.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmanagement.appointment.dto.AppointmentDto;
import com.hospitalmanagement.appointment.model.Appointment;
import com.hospitalmanagement.appointment.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}

	// creating Appointment
	@PostMapping("/create")
	@Operation(summary = "Schedule Appointment")
	public ResponseEntity<Appointment> scheduleAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment appointCreated = appointmentService.scheduleAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointCreated);
	}

	// get Appointment
	@GetMapping("/all")
	@Operation(summary = "List All Appointment")
	public ResponseEntity<List<Appointment>> getAllAppointment() {

		List<Appointment> allAppointment = appointmentService.getAllAppointment();
		return ResponseEntity.status(HttpStatus.OK).body(allAppointment);
	}

	// get Appointment By id
	@GetMapping("/id/{appointmentId}")

	@Operation(summary = "Getting Confirm Appointment")
	public ResponseEntity<Appointment> confirm(@PathVariable long appointmentId) {
		Appointment confirmed = appointmentService.confirmAppointment(appointmentId);
		return ResponseEntity.status(HttpStatus.OK).body(confirmed);

	}

	// update Appointment
	@PutMapping("/update/{appointmentId}")
	@Operation(summary = "Update Appointment")
	public ResponseEntity<Appointment> update(@Valid @RequestBody Appointment appointment,
			@PathVariable long appointId) {
		Appointment updatedAppointment = appointmentService.updateAppointment(appointment, appointId);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedAppointment);

	}

	// delete Appointment
	@DeleteMapping("/delete/{appointmentId}")
	@Operation(summary = "Update Appointment")
	public ResponseEntity<Appointment> cancel(@PathVariable long appointmentId) {
		Appointment cancel = appointmentService.cancelAppointment(appointmentId);
		return ResponseEntity.status(HttpStatus.OK).body(cancel);

	}

	// getting PatientById
	@GetMapping("/pid/{PatientId}")
	@Operation(summary = "Getting Appointment of PatientById")

	ResponseEntity<AppointmentDto> getPatient(@PathVariable long PatientId) {
		AppointmentDto patientById = appointmentService.getPatientById(PatientId);
		return ResponseEntity.status(HttpStatus.OK).body(patientById);
	}

	// getting DoctorById
	@GetMapping("did/{doctorId}")
	@Operation(summary = "Getting Doctor by DcotorId")
	ResponseEntity<AppointmentDto> getDoctor(@PathVariable long doctorId) {
		AppointmentDto doctorById = appointmentService.getDoctorById(doctorId);
		return ResponseEntity.status(HttpStatus.OK).body(doctorById);
	}
}

package com.hospitalmanagement.patient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmanagement.patient.model.Patient;
import com.hospitalmanagement.patient.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
@Tag(name ="Patient Api")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	//Creating patient
	@Operation(summary = "Creating Patient")
	@PostMapping("/create")
	public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient){
		Patient patient1 = patientService.createPatient(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(patient1);
	}
	//get All Patient
	@Operation(summary = "Getting All Patient")
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatient(){
		List<Patient> allPatient = patientService.getAllPatient();
		return ResponseEntity.status(HttpStatus.OK).body(allPatient);
	}
	//get patientBy Id
	@Operation(summary = "Getting  Patient By Id")
	@GetMapping("/id/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable long patientId){
		Patient patientById = patientService.getPatientById(patientId);
		return ResponseEntity.status(HttpStatus.OK).body(patientById);
	}
	//Delete Patient By Id
	@Operation(summary = "Delete Patient By Id")
	@DeleteMapping("/delete/{patientId}")
	public ResponseEntity<Object>deletePatient(@PathVariable long patientId){
		Map<Object,String> removePatient = new HashMap<>();
		removePatient.put(patientService.deletePatient(patientId),"Patient is Sucessfully Deleted with ID:-"+ patientId);
		return ResponseEntity.status(HttpStatus.OK).body(removePatient);
	}
	//update Patient 
	@Operation(summary = "Update Patient By Id")
	@PutMapping("/update/{patientId}")
	public ResponseEntity<Patient> updateCustomer(@Valid @RequestBody Patient patient,@PathVariable long patientId){
		Patient updatePatient =patientService.updateCustomer(patient, patientId);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatePatient);	
	}
	// Serach By Name
	@Operation(summary = "Search Patient By name")
	@GetMapping("/name")
	public ResponseEntity<List<Patient>> findByName(@RequestParam String name){
		List<Patient> patientByName = patientService.findByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(patientByName);
	}

}

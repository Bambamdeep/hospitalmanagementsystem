package com.example.doctor.Controller;

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

import com.example.doctor.model.Doctor;
import com.example.doctor.service.DoctorService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctor")
@Tag(name = "Doctor  Api")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}

	// creating Doctor
	@PostMapping("/create")
	@Operation(summary = "Creating Doctor")
	public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor saveDoctor = doctorService.createDoctor(doctor);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveDoctor);
	}

	// All Doctor
	@GetMapping("all")
	@Operation(summary = "Feteching All Doctor")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		List<Doctor> allDoctor = doctorService.getAllDoctor();
		return ResponseEntity.status(HttpStatus.OK).body(allDoctor);
	}

	// getting doctor by Id
	@GetMapping("/id/{doctorId}")
	@Operation(summary = "Feteching Doctor By Id")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable long doctorId) {
		Doctor doctorById = doctorService.getDoctorById(doctorId);
		return ResponseEntity.status(HttpStatus.OK).body(doctorById);

	}

	// update Doctor
	@PutMapping("/update/{doctorId}")
	@Operation(summary = "Update Doctor By Id")
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor, @PathVariable long doctorId) {
		Doctor updatedDoctor = doctorService.updateDoctor(doctor, doctorId);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedDoctor);
	}

	// Delete Doctor
	@DeleteMapping("/delete/{doctorId}")
	@Operation(summary = "Delete Doctor By Id")
	public ResponseEntity<Object> deleteDoctor(@PathVariable long doctorId) {
		Map<Object, String> deleteDoctor = new HashMap<>();
		deleteDoctor.put(doctorService.deleteDoctor(doctorId), "Doctor Removed Sucessfully :- " + doctorId);
		return ResponseEntity.status(HttpStatus.OK).body(deleteDoctor);
	}
	
    // get doctor by specilization
	@GetMapping("/specilization/{specilization}")
	@Operation(summary = "Getting Doctor By their specilization ")
	public ResponseEntity<List<Doctor>> findBySpecilization(@RequestParam String specilization) {
		List<Doctor> doctorBySpecilization = doctorService.findBySpecilization(specilization);
		return ResponseEntity.status(HttpStatus.OK).body(doctorBySpecilization);
	}

	// get doctor by department id
	@GetMapping("dId/{departemtId}")
	@Operation(summary = "Getting Doctor By their DepartmentId ")
	public ResponseEntity<List<Doctor>> findByDepartmetId(@PathVariable long departemtId){
		List<Doctor> departmentByDoctor = doctorService.findByDepartmetId(departemtId);
		return ResponseEntity.status(HttpStatus.OK).body(departmentByDoctor);
	}

}

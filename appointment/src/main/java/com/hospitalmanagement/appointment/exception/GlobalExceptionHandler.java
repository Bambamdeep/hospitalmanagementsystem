package com.hospitalmanagement.appointment.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("There is  No Appointment Schedule ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PatientIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePatientIdNotFoundException(PatientIdNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Patient Not Available ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DoctorIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDoctorIdNotFoundException(DoctorIdNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Doctor Not Available ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	// DoctorServiceNotAvailableException
	@ExceptionHandler(DoctorServiceNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleDoctorServiceNotAvailableException(DoctorServiceNotAvailableException ex) {
		ErrorResponse error = new ErrorResponse("Opps Doctor Service down ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler( PatientServiceUnavailableException.class)
	public ResponseEntity<ErrorResponse> handlePatientServiceUnavailableException( PatientServiceUnavailableException ex) {
		ErrorResponse error = new ErrorResponse("Patient service is undermaintance ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HashMap<String, String> handlMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		HashMap<String, String> map = new HashMap<String, String>();
		e.getBindingResult().getFieldErrors().forEach((ex) -> {
			map.put(ex.getField(), ex.getDefaultMessage());
		});
		return map;
	}

}

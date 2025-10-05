package com.hospitalmanagement.billing.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BillIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBillIdNotFoundException(BillIdNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Bill Not Generated ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePatientNotFoundException(PatientNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Patient Not Found ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(PatientServiceNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handlePatientServiceNotAvailableException(PatientServiceNotAvailableException ex) {
		ErrorResponse error = new ErrorResponse("Patient Service Under Maintanance Try After Some time ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("There is  No Appointment Schedule ", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
 
	@ExceptionHandler(AppointmentServiceNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentServiceNotAvailableException(AppointmentServiceNotAvailableException ex) {
		ErrorResponse error = new ErrorResponse("Opps Appointment Down  ", ex.getMessage());
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

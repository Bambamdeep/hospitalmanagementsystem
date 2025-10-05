package com.hospitalmanagement.appointment.exception;

public class PatientIdNotFoundException extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public PatientIdNotFoundException(String message) {
	super(message);
}
}

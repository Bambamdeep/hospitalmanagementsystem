package com.hospitalmanagement.appointment.exception;

public class PatientServiceUnavailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientServiceUnavailableException(String message) {
		super(message);
	}

}

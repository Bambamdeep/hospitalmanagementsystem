package com.example.doctor.exception;

public class DoctorIdNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoctorIdNotFoundException (String message) {
		super(message);
	}

}

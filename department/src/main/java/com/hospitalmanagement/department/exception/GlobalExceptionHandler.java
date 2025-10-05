package com.hospitalmanagement.department.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DepartmentIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePatientNotFoundException(DepartmentIdNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("Department Not Found ", ex.getMessage());
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

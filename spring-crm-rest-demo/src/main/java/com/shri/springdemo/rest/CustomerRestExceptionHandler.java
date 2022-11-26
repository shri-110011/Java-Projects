package com.shri.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// Add an exception handler to handle CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(404);
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	// Add another exception handler ... to catch any exception (catch all)
	
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		
		// create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis() 
				);
		
		// return response entity
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
}

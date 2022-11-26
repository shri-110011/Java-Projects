package com.shri.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* Note: Here we use @ControllerAdvice for global exception handling.
 * Without this the exception handler could only be used in a specific
 * RestController or Rest Service and it could be used by multiple 
 * RestControllers.
 * 
 * To enable reuse and to centralize the exception handlers we make use 
 * of @ControllerAdvice.
 * 
 * And this is the best practice.
 * 
 * Also if we have exception handlers to handle the same type of exception
 * in both @RestController and in @ControllerAdvice then the one in @RestController
 * would run.
 * 
 */
@ControllerAdvice
public class StudentRestExceptionHandler {
	
	// add exception handling code here
	// Add and exception handler using @ExceptionHandler
	
		/* @ExceptionHandler tells that this method is an exception handler.
		 * The type parameter(T) for the ResponseEntity<T> tells the type of the response body.
		 * The method parameter for this exception handler method tells the type of exception that 
		 * this handler would handle.
		 * 
		 */
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
			
			// create a student error response
			StudentErrorResponse error = new StudentErrorResponse();
			
			System.out.println("Ok3");
			
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());

			// return the response entity
			return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
		}
		
		// add another exception handler ... to catch any exception (catch all)
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
			
			// create a student error response
			StudentErrorResponse error = new StudentErrorResponse();
			
			System.out.println("Ok4");
			
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());

			// return the response entity
			return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
}

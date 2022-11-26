package com.shri.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shri.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	List<Student> theStudents;

	// define @PostConstruct to load student data only once ...
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();

		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rossi"));
		theStudents.add(new Student("Mary", "Smith"));
	}

	// define endpoint for "/students" - return list of students

	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}
	
	// define endpoint for "/students/{studentId}" - return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// just index into the list ... keep it simple for now
		
		// check the studentId against the list size
		
	
		if(studentId<0 || studentId>=theStudents.size()) {
			throw new StudentNotFoundException("Student id not found - "+studentId);
		}
	
		return theStudents.get(studentId);
	}
	
//	// Add and exception handler using @ExceptionHandler
//	
//	/* @ExceptionHandler tells that this method is an exception handler.
//	 * The type parameter(T) for the ResponseEntity<T> tells the type of the response body.
//	 * The method parameter for this exception handler method tells the type of exception that 
//	 * this handler would handle.
//	 * 
//	 */
//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
//		
//		// create a student error response
//		StudentErrorResponse error = new StudentErrorResponse();
//		
//		System.out.println("Ok1");
//		
//		error.setStatus(HttpStatus.NOT_FOUND.value());
//		error.setMessage(exc.getMessage());
//		error.setTimeStamp(System.currentTimeMillis());
//
//		// return the response entity
//		return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
//	}
//	
//	// add another exception handler ... to catch any exception (catch all)
//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//		
//		// create a student error response
//		StudentErrorResponse error = new StudentErrorResponse();
//		
//		System.out.println("Ok2");
//		
//		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setMessage(exc.getMessage());
//		error.setTimeStamp(System.currentTimeMillis());
//
//		// return the response entity
//		return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.BAD_REQUEST);
//	} 

}

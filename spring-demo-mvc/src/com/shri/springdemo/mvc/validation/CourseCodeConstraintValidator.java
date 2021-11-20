package com.shri.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
	
	private String coursePrefix; 
	
	@Override
	public void initialize(CourseCode theCode) {
		coursePrefix = theCode.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		
		boolean result = false;
		
		if(theCode != null) {
			result = theCode.startsWith(coursePrefix);
		}
		else {
			result  = true;
		}
		return result;
	}

}

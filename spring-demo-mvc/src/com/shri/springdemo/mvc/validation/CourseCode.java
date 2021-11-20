package com.shri.springdemo.mvc.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface CourseCode {

	//define default course code
	public String value() default "LUV";
	
	//define default error message
	public String message() default "must start with LUV";

	//define default groups
	//Note: You can't change this attribute name
	//It has to be groups
	public Class<?>[] groups() default {};
	
	//define default payloads
	//Note: You can't change this attribute name
	//It has to be payload
	public Class<? extends Payload>[] payload() default {};
	
}

package com.shri.springdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.shri.springdemo.entity.Customer;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.shri.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.shri.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.shri.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display the methods we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info(theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object args[] = theJoinPoint.getArgs();
		
		// loop through and display the arguments
		for(Object arg: args) {
			myLogger.info("=====>>> argument: "+arg);
		}
		
	}
	
	// add @AfterReturning advice
	@AfterReturning(
		pointcut = "forAppFlow()",
		returning = "theResult"	
	)
	public void afterReturning(JoinPoint theJoinPoint, List<Customer> theResult) {
		
		// display the methods we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info(theMethod);
		
		// display the data returned
		myLogger.info("=====>>> result: "+theResult);
	}
	
}

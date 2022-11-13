package com.shri.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {
	
	private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		TrafficFortuneService trafficFortuneService = context.getBean(TrafficFortuneService.class);
		
		// Because of the MyLoggerConfig.java file we see the logs twice in console.
		myLogger.info("\nMain Program: AroundDemoApp");
		
		myLogger.info("Calling getFortune");
		
		String data = trafficFortuneService.getFortune();
		
		myLogger.info("\nMy fortune is: "+data);
		
		myLogger.info("Finished");
		
		// close the context
		context.close();

	}

}

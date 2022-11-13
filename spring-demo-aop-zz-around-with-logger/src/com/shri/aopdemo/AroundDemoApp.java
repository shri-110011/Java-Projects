package com.shri.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shri.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {
		
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the AccountDAO bean from spring container
		TrafficFortuneService trafficFortuneService = context.getBean(TrafficFortuneService.class);
		
		System.out.println("\nMain Program: AroundDemoApp");
		
		System.out.println("Calling getFortune");
		
		String data = trafficFortuneService.getFortune();
		
		System.out.println("\nMy fortune is: "+data);
		
		System.out.println("Finished");
		
		// close the context
		context.close();

	}

}

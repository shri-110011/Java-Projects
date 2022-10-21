package com.shri.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopePracticeApp {

	public static void main(String[] args) {
		// load the configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

		// get the bean from the spring container
		CricketCoach cricketCoach1 = context.getBean("myCricketCoach", CricketCoach.class);

		CricketCoach cricketCoach2 = context.getBean("myCricketCoach", CricketCoach.class);

		// call the methods on the bean
		boolean result = cricketCoach1 == cricketCoach2;

		System.out.println("\nPointing to same object: " + result);

		System.out.println("\nMemory location for theCoach: " + cricketCoach1);

		System.out.println("\nMemory location for alphaCoach: " + cricketCoach2);

		// close the context
		context.close();
	}

}

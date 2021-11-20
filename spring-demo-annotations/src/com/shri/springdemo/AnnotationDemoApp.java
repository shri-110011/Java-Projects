package com.shri.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {

		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the beans from the spring container
		Coach theCoach = context.getBean("myBasketballCoach", Coach.class);
		
		//call the methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		//call the method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		System.out.println(theCoach.getEmail());
	
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();
	}

}

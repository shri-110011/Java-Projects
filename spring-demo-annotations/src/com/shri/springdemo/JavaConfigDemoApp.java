package com.shri.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {

		//load the spring configuration file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		//get the beans from the spring container
		Coach theCoach = context.getBean("swimCoach", Coach.class);
		
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

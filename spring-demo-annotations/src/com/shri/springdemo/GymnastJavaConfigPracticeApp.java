package com.shri.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GymnastJavaConfigPracticeApp {

	public static void main(String[] args) {
		
		//load the spring configuration class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfigPractice.class);
		
		//get the bean from the spring container
		GymnastCoach theCoach = context.getBean("gymnastCoach", GymnastCoach.class);
		
		//call the methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		System.out.println(theCoach.getDailyFortune());
		
		System.out.println(theCoach.getEmail());
		
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();
	}

}

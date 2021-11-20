package com.shri.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationPracticeApp {

	public static void main(String[] args) {
		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the beans from the spring container
		Coach theCoach = context.getBean("myBasketballCoach", Coach.class);
		
		//call the methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		System.out.println(theCoach.getDailyFortune());
		
		//close the context
		context.close();

	}

}

package com.shri.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from the spring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
		//check if the beans are same
		
		boolean result = theCoach == alphaCoach;
		
		//print out the result
		System.out.println("\nPointing to the same object: "+result);
		
		System.out.println("\nMemory location of theCoach:"+ theCoach);
		
		System.out.println("\nMemory location of alphaCoach:"+ alphaCoach);
		
		//close the context
		context.close();
		
	}

}

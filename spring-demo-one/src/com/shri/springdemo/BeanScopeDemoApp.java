package com.shri.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		//load the configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		//get the bean from the spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		//call the methods on the bean
		boolean result = theCoach==alphaCoach;
		
		System.out.println("\nPointing to same object: "+result);
		
		System.out.println("\nMemory location for theCoach: "+theCoach);
		
		System.out.println("\nMemory location for alphaCoach: "+alphaCoach);
		
		//close the context
		context.close();
	}

}

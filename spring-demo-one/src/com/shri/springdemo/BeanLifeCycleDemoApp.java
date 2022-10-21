package com.shri.springdemo;

import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		// load the configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"beanLifeCycle-applicationContext.xml");

		// get the bean from the spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);

		// call the methods on the bean
		System.out.println(theCoach.getDailyWorkout());

		// close the context
		context.close();

	}

}

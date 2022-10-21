package com.shri.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/*
 * Note: While configuring the spring container using java code the
 * @ComponentScan(package-name) annotation is optional.
 * 
 * Even if we don't provide the package-name to the @ComponentScan
 * then it will scan the current package.
 */
@Configuration
@ComponentScan("com.shri.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfigPractice {

	/* If we don't provide any bean id like this @Bean("bean-id")
	 * and just write @Bean or @Bean() the default bean name would be 
	 * the class name with the first letter in lower case.
	 */
	@Bean
	public Coach gymnastCoach() {
		System.out.println("Inside SportConfigPractice: inside gymnastCoach()");
		return new GymnastCoach(restFortuneService());
	}
	
	@Bean
	public FortuneService restFortuneService() {
		System.out.println("Inside SportConfigPractice: inside restFortuneService()");
		return new RESTFortuneService();
	}
}

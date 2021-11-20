package com.shri.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.shri.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfigPractice {

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

package com.shri.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.shri.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {
	
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
	
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}

}

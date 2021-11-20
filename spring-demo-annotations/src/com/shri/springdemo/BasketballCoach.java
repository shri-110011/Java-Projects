package com.shri.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("myBasketballCoach")
@PropertySource("sport.properties")
public class BasketballCoach implements Coach {
	
	@Value("${foo.basketball.email}")
	private String email;
	
	@Value("${foo.basketball.team}")
	private String team;
	
	private FortuneService fortuneService;
	
	@Autowired
	public BasketballCoach(@Qualifier("databaseFortuneService") FortuneService theFortuneService) {
		System.out.println("Inside BasketballCoach: inside BasketballCoach()");
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Do shooting practice for 30 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}
	
}

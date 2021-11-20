package com.shri.springdemo;

import org.springframework.beans.factory.annotation.Value;

public class GymnastCoach implements Coach {
	
	private FortuneService fortuneService;
	
	@Value("${foo.gymnast.email}")
	private String email;
	
	@Value("${foo.gymnast.team}")
	private String team;

	public GymnastCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Do stretching exercise for 30 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getTeam() {
		return team;
	}

}

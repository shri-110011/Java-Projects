package com.shri.springdemo;

public class CricketCoach implements Coach {

	private FortuneService fortuneService;
	
	//adding new fields for emailAddress and team
	private String emailAddress;
	private String team;
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		System.out.println("Inside setter method: setEmailAddress()");
		System.out.println("emailAddress: "+emailAddress);
		this.emailAddress = emailAddress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		System.out.println("Inside setter method: setTeam()");
		System.out.println("team: "+team);
		this.team = team;
	}

	public CricketCoach() {
		System.out.println("Insie no args constructor: CricketCoach()");
	}
	
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("Inside setter method: setFortuneService()");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}

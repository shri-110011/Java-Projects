package com.shri.springdemo;

public class TrackCoach implements Coach {

	private FortuneService fortuneService;
	public TrackCoach() {
		
	}
	public TrackCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	@Override
	public String getDailyWorkout() {
		return "Run a hard 5km";
	}
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	//add an init method
	public void doMyStartupStuff() {
		System.out.println("Inside TrackCoah: inside doMyStartupStuff()");
	}
	
	//add a destroy method
	public void doMyCleanupStuffYoYo() {
		System.out.println("Inside TrackCoah: inside doMyCleanupStuffYoYo()");
	}
	
}

package com.shri.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class TennisCoach implements Coach {
//	@Autowired
//	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	@Value("${foo.tennis.email}")
	private String email;
	
	@Value("${foo.tennis.team}")
	private String team;
	
	//define a default constructor
//	public TennisCoach() {
//		System.out.println(">> TennisCoach: Inside default constructor TennisCoach()");
//	}
	
	//define the init method
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println(">>Inside TennisCoach: inside doMyStartUpStuff()");
	}
	
	//define the destroy method
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println(">>Inside TennisCoach: inside doMyCleanUpStuff()");
	}
	
	//define a setter method
	/*@Autowired
	public void setFortuneService(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
		System.out.println(">> TennisCoach: Inside setter method setFortuneService()");
	}*/
	
	/*@Autowired
//	@Qualifier("randomFortuneService")
	public void doSomeCrazyStuff(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
		System.out.println(">> TennisCoach: Inside setter method doSomeCrazyStuff()");
	}*/
	
	@Autowired
	public TennisCoach(@Qualifier("randomFortuneService")FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
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

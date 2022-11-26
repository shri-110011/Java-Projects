package com.shri.springboot.demo.mycoolapp1.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

	// expose "/" that return "Hello World"
	@GetMapping("/")
	public String sayHello() {
		return "Hello World! Time on server is: " + LocalDateTime.now();
	}

	// expose "/workout" for "workout"
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 15 km!";
	}

	// expose "/workout" for "workout"
	@GetMapping("/fortune")
	public String getDailyFortune() {
		return "Today is your lucky day!";
	}

}

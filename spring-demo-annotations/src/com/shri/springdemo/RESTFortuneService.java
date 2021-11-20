package com.shri.springdemo;

import org.springframework.stereotype.Component;

public class RESTFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Patience and alertness are your strengths";
	}

}

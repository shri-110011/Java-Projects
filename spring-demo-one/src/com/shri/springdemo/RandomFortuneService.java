package com.shri.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

	String[] fortunes = {"Today is your lucky day", "It is going to be a long day", "It is going to be a hard day"};
	@Override
	public String getFortune() {
		Random random = new Random();
		int index = random.nextInt(3);
		return fortunes[index];
	}

}

package ducks;

import behaviors.Flyable;
import behaviors.Quackable;

public class RedheadDuck extends Duck implements Flyable, Quackable {
	
	@Override
	public void display() {
		System.out.println("I am a real RedheadDuck duck");
	}

	@Override
	public void quack() {
		System.out.println("Quack");
	}

	@Override
	public void fly() {
		System.out.println("Fly");
	}

}

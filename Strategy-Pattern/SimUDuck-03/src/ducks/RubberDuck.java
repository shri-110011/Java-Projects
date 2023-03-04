package ducks;

import behaviors.Quackable;

public class RubberDuck extends Duck implements Quackable {
	
	@Override
	public void quack() {
		System.out.println("Squeak");
	}

	@Override
	public void display() {
		System.out.println("I am a real rubber duck");
	}

}

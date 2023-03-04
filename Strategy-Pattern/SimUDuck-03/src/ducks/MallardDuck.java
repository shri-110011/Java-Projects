package ducks;

import behaviors.Flyable;
import behaviors.Quackable;

public class MallardDuck extends Duck implements Flyable, Quackable {

	@Override
	public void display() {
		System.out.println("I am a real Mallard duck");
	}

	@Override
	public void fly() {
		System.out.println("Fly");
	}

	@Override
	public void quack() {
		System.out.println("Quack");
	}
	
}

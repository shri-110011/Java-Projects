package ducks;

import behavior.fly.FlyWithWings;
import behavior.quack.Quack;

public class RedheadDuck extends Duck {
	
	public RedheadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
	
	@Override
	public void display() {
		System.out.println("I am a real RedheadDuck duck");
	}

}

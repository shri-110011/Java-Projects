package ducks;

import behavior.fly.FlyNoWay;
import behavior.quack.MuteQuack;

public class WoodenDuck extends Duck {
	
	public WoodenDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new MuteQuack();
	}
	
	@Override
	public void display() {
		System.out.println("I am a wodden duck");
	}
	
}

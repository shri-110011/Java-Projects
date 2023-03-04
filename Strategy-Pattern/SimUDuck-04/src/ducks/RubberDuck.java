package ducks;

import behavior.fly.FlyNoWay;
import behavior.quack.Squeak;

public class RubberDuck extends Duck {

	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}
	
	@Override
	public void display() {
		System.out.println("I am a real rubber duck");
	}

}

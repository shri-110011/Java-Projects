package ducks;

import behavior.fly.FlyBehavior;
import behavior.quack.QuackBehavior;

public class Duck {
	
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public void performFly() {
		flyBehavior.fly();
	}
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void swim() {
		System.out.println("Swim");
	}
	
	public void display() {
		System.out.println("I am a duck");
	}

	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

}

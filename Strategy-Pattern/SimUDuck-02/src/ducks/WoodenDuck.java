package ducks;

public class WoodenDuck extends Duck {

	@Override
	public void quack() {
		System.out.println("I am mute");
	}

	@Override
	public void fly() {
		System.out.println("I can't fly");
	}

	@Override
	public void display() {
		System.out.println("I am a wodden duck");
	}
	
}

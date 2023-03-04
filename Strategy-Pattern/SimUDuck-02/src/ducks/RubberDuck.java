package ducks;

public class RubberDuck extends Duck  {
	
	@Override
	public void quack() {
		System.out.println("Squeak");
	}

	@Override
	public void fly() {
		System.out.println("I can't fly");
	}

	@Override
	public void display() {
		System.out.println("I am a real rubber duck");
	}

}

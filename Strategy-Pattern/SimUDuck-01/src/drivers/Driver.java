package drivers;

import ducks.MallardDuck;
import ducks.RubberDuck;

public class Driver {

	public static void main(String[] args) {
		MallardDuck mallardDuck = new MallardDuck();
		mallardDuck.quack();
		mallardDuck.swim();
		mallardDuck.fly();
		mallardDuck.display();
		
		RubberDuck rubberDuck = new RubberDuck();
		rubberDuck.quack();
		rubberDuck.swim();
		
		// Because of inheritance RubberDuck is flying
		rubberDuck.fly();
		rubberDuck.display();		
	}
	
}

package drivers;

import ducks.MallardDuck;
import ducks.RubberDuck;
import ducks.WoodenDuck;

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
		
		/* Because of interface we just implement those behaviors that a
		 * Duck subclass is supposed to have.
		 * 
		 * But by using interface we now have duplicate code in the subclasses
		 * of Duck that represents a duck species. So with addition of every 
		 * subclass of Duck we may need to override the fly() or quack().
		 * 
		 * Also if we have a large number of subclasses that override the fly()
		 * or quack() then a small change in those behaviors will require changes to be
		 * done to all those subclasses.
		 */
		
//		rubberDuck.fly(); // compile error
		rubberDuck.display();
		
		WoodenDuck woodenDuck = new WoodenDuck();
//		woodenDuck.quack(); // compile error
//		woodenDuck.fly(); // compile error
		woodenDuck.swim();
		woodenDuck.display();
		
	}
	
}

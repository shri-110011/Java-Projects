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
		
		/* Because of inheritance RubberDuck is flying,
		 * so we override that fly() in RubberDuck.
		 * 
		 * Also RubberDuck don't quack so we overrode the
		 * quack() in RubberDuck. 
		 * 
		 */
		rubberDuck.fly();
		rubberDuck.display();
		
		/* For WoddenDuck, it can't quack or squeak and also can't fly
		 * so we overrode the fly() and quack() in WoodenDuck.
		 * 
		 * Conclusion: With every new subclass of Duck added to this SimUDuck
		 * app we may need to override the Duck's behavior methods for that
		 * duck based on its type i.e. WoodenDuck/RubberDuck or any other
		 * living duck species.
		 * 
		 */
		WoodenDuck woodenDuck = new WoodenDuck();
		woodenDuck.quack();
		woodenDuck.fly();
		woodenDuck.display();
		
	}
	
}

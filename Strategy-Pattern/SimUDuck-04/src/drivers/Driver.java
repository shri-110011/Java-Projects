package drivers;

import behavior.fly.FlyRocketPowered;
import ducks.MallardDuck;
import ducks.RubberDuck;
import ducks.WoodenDuck;

/* Strategy pattern in action
 * 
 */
public class Driver {

	public static void main(String[] args) {
		MallardDuck mallardDuck = new MallardDuck();
		mallardDuck.performQuack();
		mallardDuck.swim();
		mallardDuck.performFly();
		mallardDuck.display();
		
		RubberDuck rubberDuck = new RubberDuck();
		rubberDuck.performQuack();
		rubberDuck.swim();
		rubberDuck.performFly();
		rubberDuck.display();
		
		WoodenDuck woodenDuck = new WoodenDuck();
		woodenDuck.performQuack();
		woodenDuck.swim();
		woodenDuck.performFly();
		woodenDuck.display();
		
		woodenDuck.setFlyBehavior(new FlyRocketPowered());
		woodenDuck.performFly();
		
	}
	
}

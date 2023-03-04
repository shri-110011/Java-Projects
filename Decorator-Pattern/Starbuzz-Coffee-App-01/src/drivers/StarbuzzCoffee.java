package drivers;

import beverages.Beverage;
import beverages.DarkRoast;
import beverages.Espresso;

public class StarbuzzCoffee {

	public static void main(String[] args) {
		
		/* In this version of StarbuzzCoffee app we are using inheritance to extend the functionality of
		 * the base class Beverage.
		 * Also we have cost() method that is overridden in the subclasses of Beverage.
		 * The cost() in Beverage computes the cost of Condiments added on top of the base beverage type.
		 * The cost() in the subclasses of Beverage computes the cost of that beverage type and then delegates 
		 * the computation of the cost of Condiments to its super class cost().
		 * 
		 * Disadvantage of this approach:
		 * 1. Whenever any new Beverage type is added cost() has to be overridden.
		 * 2. Whenever the prices of Condiments change we need to change the existing code i.e. make changes in 
		 * the Beverage class.
		 * 3. New Condiments will also require us to change the existing code.
		 * 4. When Customer request for Double Mocha with DarkRoast then the cost can't be calculated correctly 
		 * using this existing approach.
		 * 
		 */
		Beverage beverage = new Espresso();
		beverage.setMilk(true);
		beverage.setMocha(true);
		
		System.out.println("Cost for " + beverage.getDescription() + " is: $" + beverage.cost());
		
		beverage = new DarkRoast();
		System.out.println("Cost for " + beverage.getDescription() + " is: $" + beverage.cost());
	}

}

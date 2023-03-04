package drivers;

import pizzas.Pizza;
import pizzastores.PizzaStore;
import pizzastores.SimplePizzaFactory;

public class PizzaTestDrive {

	public static void main(String[] args) {
		
		/* In this approach we have the class PizzaStore that has the orderPizza() which is a client of 
		 * the SimplePizzaFactory.
		 * 
		 * Advantage of SimplePizzaFactory:
		 * 1. Multiple clients like the PizzaShopMenu and HomeDelivery classes may also get the correct
		 * pizza object based on the type from this SimplePizzaFactory.
		 * 
		 * 2. And by encapsulating the object creation we now have just a single class that is to be modified
		 * when new concrete pizza classes are implemented.
		 * 
		 * Disadvantages of SimplePizzaFactory:
		 * 1. Using this kind of factory we won't be able to handle the scenario where we need to franchise our
		 * pizza store. In which case the franchises could offer their own style of pizzas depending on regional
		 * differences and the taste of the local pizza connoisseurs.
		 * 
		 * Note: This SimplePizzaFactory is not the Factory design pattern it is a "programming idiom".
		 * 
		 * "programming idiom" - It is a group of code fragments that represents the usual way to code a 
		 * task.
		 * 
		 */
		
		SimplePizzaFactory factory = new SimplePizzaFactory();
		
		PizzaStore pizzaStore = new PizzaStore(factory);
		Pizza pizza = pizzaStore.orderPizza("cheese");
		
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);

	}

}

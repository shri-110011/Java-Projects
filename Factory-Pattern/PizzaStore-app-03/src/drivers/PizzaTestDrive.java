package drivers;

import pizzas.Pizza;
import pizzastores.ChicagoPizzaStore;
import pizzastores.NYPizzaStore;
import pizzastores.PizzaStore;

public class PizzaTestDrive {

	public static void main(String[] args) {
		
		/* In this version of PizzaStore-app we use the Factory Method pattern. That means we have a framework 
		 * or an interface for creating an object which lets the subclasses decide which class to instantiate. 
		 * In our case the PizzaStore abstract class is the interface which contains an abstract method which
		 * is called the factory method: 
		 * abstract public Pizza createPizza(String type);
		 * 
		 * And for each franchise we created a concrete class which extends the PizzaStore class and implements
		 * the interface createPizza() provided by PizzaStore. And depending on which store we order from the
		 * type of concrete Pizza class that will be instantiated will be decided.
		 * 
		 */
		
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");
 
	}

}

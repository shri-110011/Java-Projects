package drivers;

import pizzas.Pizza;
import pizzastores.ChicagoPizzaStore;
import pizzastores.NYPizzaStore;
import pizzastores.PizzaStore;

public class PizzaTestDrive {

	public static void main(String[] args) {
		
		/* In this version of PizzaStore-app we have used the Abstract Factory pattern.
		 * Abstract Factory pattern provides an interface that allows us to create a family of products.
		 * 
		 * In this case we have the PizzaIngredientFactory which provides us the interface to create a 
		 * family of products. 
		 * 
		 * Abstract factory pattern relies on composition to create the family of products. For instance 
		 * we compose the concrete CheesePizza object with a PizzaIngredientFactory reference variable 
		 * that hold a reference to an appropriate PizzaIngredientFactory implementation. So that when the 
		 * prepare() is called the right style of pizza gets created.
		 * 
		 * Note: We also are using the Factory Method pattern for creating varieties of pizza based on 
		 * region using the createPizza() factory method of the PizzaStore.
		 * 
		 * The Factory Method pattern relies on inheritance to create the appropriate object and it just 
		 * handles the creation of one object as opposed to the Abstract Factory pattern which creates a 
		 * family of products, for instance the NYPizzaIngredientFactory creates the ingredients required
		 * to make NY style pizzas. And these ingredients belong to the NY family.
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

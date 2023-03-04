package drivers;

import pizzas.Pizza;
import pizzastores.PizzaStore;

public class PizzaTestDrive {

	public static void main(String[] args) {
		
		/* In this approach we have the class PizzaStore that handles the correct pizza object creation.
		 * 
		 * And it contains code that varies(which is the types of pizza objects to be created) because with
		 * time more concrete pizza classes representing more varieties of pizza have to be created.
		 *  
		 * And it also contains code that stays the same i.e. the prepare(), bake(), cut(), box().
		 * 
		 * The down side of using this approach is that right now the task of creating the pizza is locked 
		 * in the client code(which is the PizzaStore order()) but there may be other clients which need a correct
		 * pizza object based on the type. 
		 * So next we have to remove what varies aspect from the PizzaStore and
		 * encapsulate it into a class which we call as a "factory" and its sole purpose is to handle the 
		 * details of object creation(in this case the pizza object creation).  
		 * 
		 */
		
		PizzaStore pizzaStore = new PizzaStore();
		Pizza pizza = pizzaStore.orderPizza("cheese");
		
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);

	}

}

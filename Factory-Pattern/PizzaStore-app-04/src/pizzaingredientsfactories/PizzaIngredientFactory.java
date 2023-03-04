package pizzaingredientsfactories;

import pizzaingredients.Cheese;
import pizzaingredients.Clams;
import pizzaingredients.Dough;
import pizzaingredients.Pepperoni;
import pizzaingredients.Sauce;
import pizzaingredients.Veggies;

public interface PizzaIngredientFactory {
	
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();

}

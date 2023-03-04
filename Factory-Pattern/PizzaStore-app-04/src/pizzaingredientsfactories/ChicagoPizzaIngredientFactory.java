package pizzaingredientsfactories;

import pizzaingredients.BlackOlives;
import pizzaingredients.Cheese;
import pizzaingredients.Clams;
import pizzaingredients.Dough;
import pizzaingredients.Eggplant;
import pizzaingredients.FrozenClams;
import pizzaingredients.MozzarellaCheese;
import pizzaingredients.Pepperoni;
import pizzaingredients.PlumTomatoSauce;
import pizzaingredients.Sauce;
import pizzaingredients.SlicedPepperoni;
import pizzaingredients.Spinach;
import pizzaingredients.ThickCrustDough;
import pizzaingredients.Veggies;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
	
	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(), 
		                      new Spinach(), 
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}

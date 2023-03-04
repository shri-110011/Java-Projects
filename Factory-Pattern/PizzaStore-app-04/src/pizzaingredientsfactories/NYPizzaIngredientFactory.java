package pizzaingredientsfactories;

import pizzaingredients.Cheese;
import pizzaingredients.Clams;
import pizzaingredients.Dough;
import pizzaingredients.FreshClams;
import pizzaingredients.Garlic;
import pizzaingredients.MarinaraSauce;
import pizzaingredients.Mushroom;
import pizzaingredients.Onion;
import pizzaingredients.Pepperoni;
import pizzaingredients.RedPepper;
import pizzaingredients.ReggianoCheese;
import pizzaingredients.Sauce;
import pizzaingredients.SlicedPepperoni;
import pizzaingredients.ThinCrustDough;
import pizzaingredients.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinaraSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), 
							  new Onion(), 
							  new Mushroom(),
							  new RedPepper()
							};
		return veggies;
	}

	@Override
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClam() {
		return new FreshClams();
	}

}

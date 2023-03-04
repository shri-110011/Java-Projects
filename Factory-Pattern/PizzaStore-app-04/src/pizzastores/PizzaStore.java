package pizzastores;

import pizzas.Pizza;

public abstract class PizzaStore {
	
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	abstract protected Pizza createPizza(String type);

}

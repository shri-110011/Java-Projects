package condiments;

import beverages.Beverage;

public class Soy extends CondimentDecorator {
	
	private Beverage beverage;
	
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription()+", Soy";
	}

	@Override
	public double cost() {
		return roundedValue(beverage.cost() + 0.15);
	}

}

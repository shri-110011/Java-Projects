package condiments;

import beverages.Beverage;

public class Milk extends CondimentDecorator {
	
	private Beverage beverage;
	
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription()+", Steamed Milk";
	}

	@Override
	public double cost() {
		return roundedValue(beverage.cost() + 0.10);
	}

}

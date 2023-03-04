package condiments;

import beverages.Beverage;

public class Whip extends CondimentDecorator {
	
	private Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription()+", Whip";
	}

	@Override
	public double cost() {
		return roundedValue(beverage.cost() + 0.10);
	}

}

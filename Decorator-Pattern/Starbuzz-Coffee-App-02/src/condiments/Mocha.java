package condiments;

import java.text.DecimalFormat;

import beverages.Beverage;

public class Mocha extends CondimentDecorator {
	
	private Beverage beverage;
	
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription()+", Mocha";
	}

	@Override
	public double cost() {
		return roundedValue(beverage.cost() + 0.20);
	}

}

package condiments;

import java.text.DecimalFormat;

import beverages.Beverage;

public abstract class CondimentDecorator extends Beverage {
	
	public abstract String getDescription();
	
	public double roundedValue(double val) {
		DecimalFormat df = new DecimalFormat(".##");
		String roundedResult = df.format(val);
		return Double.parseDouble(roundedResult);
	}

}

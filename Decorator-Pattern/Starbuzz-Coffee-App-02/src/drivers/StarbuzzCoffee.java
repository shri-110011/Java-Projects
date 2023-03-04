package drivers;

import beverages.Beverage;
import beverages.DarkRoast;
import beverages.Espresso;
import condiments.Milk;
import condiments.Mocha;

public class StarbuzzCoffee {

	public static void main(String[] args) {
		
		Beverage beverage = new Espresso();
		System.out.println("Cost for " + beverage.getDescription() + " is: $" + beverage.cost());
		
		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Milk(beverage2);
		System.out.println("Cost for " + beverage2.getDescription() + " is: $" + beverage2.cost());
		
//		DecimalFormat decimalFormat = new DecimalFormat(".##");
//		String str = decimalFormat.format(5.256);
//		System.out.println(str);
		
	}

}

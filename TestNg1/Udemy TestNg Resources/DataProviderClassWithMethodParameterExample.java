package dataProvider;

import org.testng.annotations.Test;

public class DataProviderClassWithMethodParameterExample {
	@Test(dataProvider="NumberInputs", dataProviderClass=MyDataProviderWithParameter.class)
	public void sumOfTwoNumbersMethodOne(int num1,int num2) {
		System.out.println("Entry of sumOfTwoNumbersMethodOne");
		int sum = num1+num2;
		System.out.println("Sum of two numbers is :" +sum);
		System.out.println("Exit of sumOfTwoNumbersMethodOne");
	}
	
	@Test(dataProvider="NumberInputs", dataProviderClass=MyDataProviderWithParameter.class)
	public void sumOfTwoNumbersMethodTwo(int num1,int num2) {
		System.out.println("Entry of sumOfTwoNumbersMethodTwo");
		
		int sum = num1+num2;
		System.out.println("Sum of two numbers is :" +sum);
		System.out.println("Exit of sumOfTwoNumbersMethodTwo");
	}
	
	

}

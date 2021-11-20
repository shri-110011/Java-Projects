package dataProvider;

import org.testng.annotations.Test;

public class DataProviderInSeperateClassExample {
	@Test(dataProvider="NumberInputs", dataProviderClass=MyDataProvider.class)
	public void sumOfTwoNumbers(int num1,int num2) {
		
		int sum = num1+num2;
		System.out.println("Sum of two numbers is :" +sum);
	}
	
	

}

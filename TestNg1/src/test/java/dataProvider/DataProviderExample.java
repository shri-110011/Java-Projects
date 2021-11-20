package dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {
	@Test(dataProvider="NumberInputs1")
	public void sumOfTwoNumbers(int num1,int num2,int num3) {
		
		int sum = num1+num2+num3;
		System.out.println("Sum of two numbers is :" +sum);
	}
	
	@DataProvider(name="NumberInputs1")
	public Object[][] getDataFromDataProvider(){
		
		return new Object[][]
				{
					{22,33,12},
					{12,13,10}
				};
	}
	

}

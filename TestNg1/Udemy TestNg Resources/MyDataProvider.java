package dataProvider;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

	@DataProvider(name="NumberInputs")
	public Object[][] getDataFromDataProvider(){
		
		return new Object[][]
				{
					{22,33},
					{12,13}
				};
	}
	
	
}

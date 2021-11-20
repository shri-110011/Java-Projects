package assertions;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertExample {
	@Test
	public void myTest() {
		
		SoftAssert softAssertion = new SoftAssert();
		//assertTrue(checkBigger(5, 10));	
		softAssertion.assertTrue(checkBigger(5,10));//If we just use softAssertion.assertTrue() our test will pass even if the assertTrue() fails and here the sysout will execute. 
		System.out.println("End of my Test");
		softAssertion.assertAll();//If we include softAssertion.assertAll() then our sysout will get executed, as well as the test will fail if assertTrue() fails.
	}
	
	@Test
	public void myTestTwo() {
		
		assertTrue(checkBigger(10, 5));	
		
	}
	public boolean checkBigger(int x, int y) {
		
		boolean result;
		if(x>y) {
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	
	
	
	
}

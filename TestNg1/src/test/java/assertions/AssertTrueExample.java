package assertions;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class AssertTrueExample {
	
	//The tests declared here are getting executed arbitrarily.
	@Test
	public void bTest() {
		
		assertTrue(checkBigger(15, 10));	
		
	}
	
	@Test
	public void aTest() {
		
		assertTrue(checkBigger(15, 10));	
		
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
	
	@Test
	public void myTestWithMessage() {
		
		assertTrue(checkBigger(15, 10), "First number is smaller than second number");
		
	}
	
	@Test
	public void myTzestWithMessage() {
		
		assertTrue(checkBigger(15, 10), "First number is smaller than second number");
		
	}
	
	
}

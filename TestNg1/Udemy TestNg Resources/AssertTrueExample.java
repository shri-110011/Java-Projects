package assertions;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class AssertTrueExample {
	@Test
	public void myTest() {
		
		assertTrue(checkBigger(5, 10));	
		
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
		
		assertTrue(checkBigger(5, 10), "First number is smaller than second number");
		
	}
	
	
}

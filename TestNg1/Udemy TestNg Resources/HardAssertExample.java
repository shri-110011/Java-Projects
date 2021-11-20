package assertions;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class HardAssertExample {
	@Test
	public void myTest() {
		
		assertTrue(checkBigger(5, 10));	
		
		System.out.println("End of my Test");
		
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

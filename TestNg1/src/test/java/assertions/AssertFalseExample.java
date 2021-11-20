package assertions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class AssertFalseExample {
	@Test
	public void myTest() {
		
		assertFalse(checkBigger(10, 5));
		
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
		
		assertFalse(checkBigger(10, 10), "First number is greater than second number");
		
	}
	
	
}

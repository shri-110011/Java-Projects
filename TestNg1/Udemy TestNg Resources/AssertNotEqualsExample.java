package assertions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class AssertNotEqualsExample {
	@Test
	public void myAssertNotEqualsBooleanTest() {
		
		assertNotEquals(checkBigger(5, 10), false);
		
		
	}
	
	@Test
	public void myAssertNotEqualsIntegerTest() {
		
		assertNotEquals(multiply(10, 5), 50);
		
	}
	
	@Test
	public void myAssertNotEqualsStringTest() {
		
		assertNotEquals(greetings("Hello"), "Hello");
		
	}
	
	public int multiply(int x, int y) {
		
		return x*y;
	}
	
	public String greetings(String message) {
		
		return message;
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
	public void myAssertNotEqualsBooleanTestWithMessage() {
		
		assertNotEquals(checkBigger(5, 10), true, "First number is greater than second number");
		
	}
	
	@Test
	public void myAssertNotEqualsIntegerTestWithMessage() {
		
		assertNotEquals(multiply(5, 10), 5, "Multiplication of two number is correct");
		
	}
	
	@Test
	public void myAssertNotEqualsStringTestWithMessage() {
		
		assertNotEquals(greetings("Hello"), "Hi", "Actual greeting message match expected greeting message");
		
	}
	
	
}

package assertions;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class AssertEqualsExample {
	@Test
	public void myAssertEqualsBooleanTest() {
		
		assertEquals(checkBigger(10, 5), true);
		
	}
	
	@Test
	public void myAssertEqualsIntegerTest() {
		
		assertEquals(multiply(10, 5), 5);
		
	}
	
	@Test
	public void myAssertEqualsStringTest() {
		
		assertEquals(greetings("Hello"), "Hello");
		
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
	public void myAssertEqualsBooleanTestWithMessage() {
		
		assertEquals(checkBigger(10, 5), true, "First number is smaller than second number");
		
	}
	
	@Test
	public void myAssertEqualsIntegerTestWithMessage() {
		
		assertEquals(multiply(10, 5), 5, "Multiplication of two number is not correct");
		
	}
	
	@Test
	public void myAssertEqualsStringTestWithMessage() {
		
		assertEquals(greetings("Hello"), "Hi", "Actual greeting message doesn't match expected greeting message");
		
	}
	
	
}

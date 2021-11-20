package assertions;

import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

public class AssertNullExample {

	String str1 = null;
	String str2 = "abc";
	
	@Test
	public void myAssertNullTest() {
		
		assertNull(str1);
		
	}
	
	@Test
	public void myAssertNullWithMessageTest() {
		
		assertNull(str1, "The string is not null");
		
	}
	
	@Test
	public void myAssertNullTestFail() {
		
		assertNull(str2);
		
	}
	
	@Test
	public void myAssertNullWithMessageTestFail() {
		
		assertNull(str2, "The string is not null");
		
	}
	
}

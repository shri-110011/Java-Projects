package assertions;

import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;

public class AssertNotSameExample {

	String str1 = "abc";
	String str2 = "abc";
	
	@Test
	public void myAssertNotSameTest() {
		
		assertNotSame(str1, str2);
		
	}
	
	@Test
	public void myAssertNotSameWithMessageTest() {
		
		assertNotSame(str1, str2, "References to Str1 is same with references to Str2");
		
	}
	
}

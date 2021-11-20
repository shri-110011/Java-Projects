package assertions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;

class Student{
    String name;
}

public class AssertSameExample {

	String str1 = "abc";
	String str2 = "abc";
	String str3 = new String("abc");
	String str4 = new String("abc");
	
	@Test
	public void myAssertSameTest() {
		if(str3 == str4) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		Student s1 = new Student();
		s1.name = "John";
		
		Student s2;
		
		s2 = s1;
		
		Student s3 = new Student();
		s3.name = "John";
		
		if(s1.name == s3.name) {
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
		
		System.out.println(s1);
		System.out.println(s3);
		
		assertSame(s1.name, s3.name);
		
		assertSame(str1, str2);
		
	}
	
	@Test
	public void myAssertSameWithMessageTest() {
		
		assertSame(str1, str2, "Reference to Str1 is not same with reference to Str2");
		
	}
	
}

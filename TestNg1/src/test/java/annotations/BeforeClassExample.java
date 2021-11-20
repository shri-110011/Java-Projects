package annotations;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BeforeClassExample {
	
	//We can have multiple methods annotated with @BeforeClass and they will be executed in the alphabetical order of their method name.
	@BeforeClass
	public void beforeClass2() {
		System.out.println("Inside beforeClass2");
	}

	//Method annotated with @BeforeClass will get executed only once before all the test cases.  
	@BeforeClass
	public void beforeClass() {
		System.out.println("Inside beforeClass");
	}
	
	
  
	//Method annotated with @BeforeMethod will get executed before each test case.  
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Inside beforeMethod");
	}
  
	@Test
	public void testMethodOne() {
		System.out.println("Inside testMethodOne");
	}
  
	@Test
	public void testMethodTwo() {
		System.out.println("Inside testMethodTwo");
	}
	
	@BeforeSuite
	public void myBeforeSuite() {
		System.out.println("Starting Selenium driver!");
	}

	@AfterSuite
	public void myAfterSuite() {
		System.out.println("Stopping Selenium driver!");
	}
}

package annotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;

public class AfterClassExample extends TestBase {
	
		@Test
		public void testMethodThree() {
			System.out.println("Inside testMethodThree");
		}
		
		@AfterClass
		public void AfterClass() {
			System.out.println("Inside afterClass");
		}
		
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
		
		@AfterMethod
		public void afterMethod() {
			System.out.println("Inside afterMethod");
		}

}

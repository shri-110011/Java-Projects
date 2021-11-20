package testNgITestListener;

import static org.testng.Assert.assertTrue;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ITestListenerTestCaseExample {
	int i;
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Inside before test");
	}
	
	@Test
	public void myTest1() {
		System.out.println("Inside myTest1");
		assertTrue(true);
	}
	
	@Test
	public void myTest2() {
		System.out.println("Inside myTest2");
		assertTrue(false);
	}
	
	@Test
	public void myTest3() {
		System.out.println("Inside myTest3");
		throw new SkipException("myTest3 is skipped");
	}
	
	@Test(successPercentage = 80, invocationCount = 5)
	public void myTest4() {
		i++;
		System.out.println("Inside myTest4, Invocation count is: "+i);
		if(i==1||i==2) {
			System.out.println("myTest4 failed");
			assertTrue(false);
		}
		else {
			System.out.println("myTest4 passed");
			assertTrue(true);
		}
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Inside after test");
	}
}

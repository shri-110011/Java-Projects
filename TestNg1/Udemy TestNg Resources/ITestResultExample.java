package testNgITestResult;

import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ITestResultExample {
	
	@Test
	public void myTestA() {
		
		System.out.println("I am in myTestA method");
		assertTrue(true);
	}

	@Test
	public void myTestB() {
		
		System.out.println("I am in myTestB method");
		assertTrue(false);
	}
	
	@AfterMethod
	public void getStatus(ITestResult myTestStatus) {
		if(myTestStatus.getStatus()==ITestResult.SUCCESS) {
			System.out.println("Pass: Method Name is " + myTestStatus.getMethod().getMethodName());
		}
		else if (myTestStatus.getStatus()==ITestResult.FAILURE) {
			System.out.println("Fail: Method Name is " + myTestStatus.getMethod().getMethodName());
		}
		
	}
}

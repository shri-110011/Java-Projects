package testNgIExecutionListener;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class IExecutionListenerTestCaseExample {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");
	}

	@Test
	public void myTestCase() {
		System.out.println("Test");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");
	}
	
}

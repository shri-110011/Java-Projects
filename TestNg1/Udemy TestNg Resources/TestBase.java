package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestBase {

	@BeforeSuite
	public void myBeforeSuite() {
		System.out.println("Starting Selenium drivers");
	}

	@AfterSuite
	public void myAfterSuite() {
		System.out.println("Stoping Selenium drivers");
	}
	
	@BeforeTest
	public void myBeforeTest() {
		System.out.println("Starting Before test");
	}

	@AfterTest
	public void myAfterTest() {
		System.out.println("Stoping After test");
	}
	

}

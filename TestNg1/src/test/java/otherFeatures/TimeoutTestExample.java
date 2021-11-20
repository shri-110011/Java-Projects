package otherFeatures;

import org.testng.annotations.Test;

public class TimeoutTestExample {
	@Test(timeOut = 10000) //The timeout at the method level has higher precedence than the time-out at the TestSuitExample.xml file. 
	public void myTest1() throws InterruptedException {
		System.out.println("Running my test 1");

		Thread.sleep(8000);
		System.out.println("Ending my test 1");
	}

	@Test
	public void myTest2() throws InterruptedException {
		System.out.println("Running my test 2");

		Thread.sleep(400);
		System.out.println("Ending my test 2");

	}

}

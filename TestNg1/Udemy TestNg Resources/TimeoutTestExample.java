package otherFeatures;

import org.testng.annotations.Test;

public class TimeoutTestExample {
	@Test(timeOut = 10000)
	public void myTest1() throws InterruptedException {
		System.out.println("Running my test 1");

		Thread.sleep(6000);
		System.out.println("Ending my test 1");
	}

	@Test
	public void myTest2() throws InterruptedException {
		System.out.println("Running my test 2");

		Thread.sleep(6000);
		System.out.println("Ending my test 2");

	}

}

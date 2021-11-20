package otherFeatures;

import org.testng.annotations.Test;

public class DisableTestExample {
	@Test(enabled = true) //By default the 'enabled' is true that's why we didn't use 'enabled' parameter if we don't want to disable any test.
	public void myTest1() {
		System.out.println("Running my test 1");
	}

	@Test
	public void myTest2() {
		System.out.println("Running my test 2");
	}

}

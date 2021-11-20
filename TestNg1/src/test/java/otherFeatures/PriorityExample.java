package otherFeatures;

import org.testng.annotations.Test;

public class PriorityExample {
	
	@Test(priority=0)
	public void myTestOne() throws InterruptedException {
		Thread.sleep(5000);//Since this method has the highest priority and even though we have specified the Thread.sleep() so other methods have to wait for this method to finish.
		System.out.println("I am in myTestOne");
	}

	@Test(priority=1)
	public void myTestTwo() {
		System.out.println("I am in myTestTwo");
	}

	@Test(priority=2)
	public void myTestThree() {
		System.out.println("I am in myTestThree");
	}

}

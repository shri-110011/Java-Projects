package otherFeatures;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class DependsOnSingleTestMethodExample {

	@Test(dependsOnMethods = "myTestB") //Note the dependent method is executed before the method that is dependent on this method and then the remaining methods can be executed in any order. 
	public void myTestA() {
		System.out.println("I am in myTestA");
		//1st failure
		//assertTrue(false);
	}

	@Test
	public void myTestB() {
		System.out.println("I am in myTestB");
		//2nd failureN
//		assertTrue(false);//Note if the dependent method fails then the method which is dependent on this method will be skipped not failed
	}

	@Test(dependsOnMethods = "myTestD")
	public void myTestC() {
		System.out.println("I am in myTestC");
	}
	
	@Test
	public void myTestD() {
		System.out.println("I am in myTestD");
	}
	
}

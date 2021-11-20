package otherFeatures;

import org.testng.annotations.Test;

public class ParentClassExample {


	@Test(dependsOnMethods = "myTestF")
	public void myTestE() {
		
		System.out.println("I am in myTestE");
		
	}
	
	@Test
	public void myTestF() {
		
		System.out.println("I am in myTestF");
		
	}
	
}

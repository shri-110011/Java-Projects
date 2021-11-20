package otherFeatures;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class DependsOnGroupsAndDependsOnMethodsTestExample {

	@Test(dependsOnGroups = "Group1")
	public void myTestA1() {
		System.out.println("###");
		System.out.println("I am in myTestA1");
		//assertTrue(false);
	}

	@Test(groups = "Group1")
	public void myTestB1() {
		System.out.println("I am in myTestB1");
		assertFalse(false);
	}

	@Test(groups = "Group1", dependsOnMethods = "myTestD1")
	public void myTestC() {
		System.out.println("I am in myTestC1");
	}
	
	@Test(groups = "Group1")
	public void myTestD1() {
		System.out.println("I am in myTestD1");
	}
}

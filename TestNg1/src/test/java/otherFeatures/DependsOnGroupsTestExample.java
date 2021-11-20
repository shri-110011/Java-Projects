package otherFeatures;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class DependsOnGroupsTestExample {

	@Test(dependsOnGroups = "Group1")
	public void myTestA() {
		System.out.println("I am in myTestA");
		//assertTrue(false);
	}

	@Test(groups = "Group1")
	public void myTestB() {
		System.out.println("I am in myTestB");
		assertFalse(false);
	}

	@Test(groups = "Group1")
	public void myTestC() {
		System.out.println("I am in myTestC");
	}

}

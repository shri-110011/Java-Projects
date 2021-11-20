package annotations;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class BeforeMethodExample {
  @Test
  public void TestMethod() {
	  System.out.println(" I am inside Test method");
  }
  @Test
  public void TestMethodTwo() {
	  System.out.println(" I am inside Test method Two");
  }
  @BeforeMethod
  public void myBeforeMethod() {
	  System.out.println(" I am inside my Before method");
  }

}

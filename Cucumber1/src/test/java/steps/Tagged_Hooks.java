package steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Tagged_Hooks {
	private WebDriver driver;
	
	public Tagged_Hooks(Common_Steps common_Steps) {
		this.driver = common_Steps.getDriver();
	}
	
	//This is scenario specific before hook or a tagged hook.
	//The order of appearance of @Before and @Before("@setCookies") hook is important if both of them appear in a same file.
	//Whichever of these @Before hooks comes first that will have higher priority and will get executed first.
	
	//While ordering tagged hooks note we use the value and order parameter.
	//order can be 0,1,2,3...
	//Lower is the order higher is the precedence.
	
	//We can have multiple tagged hooks.
	//The concept of order is there to determine precedence among global and tagged @Before and @After hooks, when the tagged hooks may not be there in the same file as the global hooks. 
	@Before(value = "@setCookies", order = 0)
	public void setCookie() {
		System.out.println("Scenario specific hook - setCookie executed");
	}
	
	//This is scenario specific after hook or a tagged hook.
	//The order of appearance of @After and @After("@Test") hook is important if both of them appear in a same file.
	//Whichever of these @After hooks comes first that will have higher priority and will get executed last.
	@After(value = "@Test", order = 0)
	public void testAfterHook() {
		System.out.println("Scenario specific hook - testAfterHook executed");
	}
}

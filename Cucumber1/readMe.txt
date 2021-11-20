cucumber junit dependency
cucumber java
cucumber picocontainer
cucumber serenity

9/6/2021:
Just before ordering hooks.
Common_Steps.java :
package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Common_Steps {
	private WebDriver driver;
	
	//@Before and @After are global hooks.
	//@Before hook is executed before each scenario gets executed for all the feature files.
	//It is not necessary to put @Before hook in the Common_Steps.java file we can specify it in any of the step definition file.
	//We can have more than one @Before hook and all will be picked up by cucumber.
	//Same goes for @After hook.
	
	//This is scenario specific before hook or a tagged hook.
	//The order of appearance of @Before and @Before("@setCookies") hook is important.
	//Whichever of these @Before hooks comes first that will have higher priority and will get executed first.
	@Before("@setCookies")
	public void setCookie() {
		System.out.println("Scenario specific hook - setCookie executed");
	}
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		System.out.println("Global Before hook executed");
	}
	
	//This is scenario specific after hook or a tagged hook.
	//The order of appearance of @After and @After("@Test") hook is important.
	//Whichever of these @After hooks comes first that will have higher priority and will get executed last.
	@After("@Test")
	public void testAfterHook() {
		System.out.println("Scenario specific hook - testAfterHook executed");
	}
	
	@After
	public void tearDown() throws InterruptedException {
		this.driver.quit();
		Thread.sleep(1000);
		System.out.println("Global After hook executed");
	}
	
	
	
	public WebDriver getDriver() {
		return this.driver;
	}

}

https://www.dezlearn.com/implementing-masterthought-reports-in-cucumber/

https://stackoverflow.com/questions/57482439/net-masterthought-cucumber-validationexception-no-report-file-was-added-error

https://stackoverflow.com/questions/63150281/unable-to-attach-screen-shot-to-cucumber-jvm-report/68676709#68676709

How to embed the screen shot captured, into the Masterthought report while using Cucumber, Selenium and Testng?
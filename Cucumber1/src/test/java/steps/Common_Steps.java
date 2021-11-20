package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Common_Steps {
	private WebDriver driver;
	
	//@Before and @After are global hooks.
	//@Before hook is executed before each scenario gets executed for all the feature files.
	//It is not necessary to put @Before hook in the Common_Steps.java file we can specify it in any of the step definition file.
	//We can have more than one @Before hook and all will be picked up by cucumber.
	//Same goes for @After hook.
	
	@Before(order = 1)
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		System.out.println("Global Before hook executed");
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) throws InterruptedException {
		if(scenario.isFailed()) {
			final byte[] scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(scr, "image/png", scenario.getName());
		}
		this.driver.quit();
		Thread.sleep(1000);
		System.out.println("Global After hook executed");
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}

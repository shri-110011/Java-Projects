package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.ScreenShotTaker;

public class CommonSteps extends AbstractTestNGCucumberTests {
	private static WebDriver driver;
	ScreenShotTaker screenShotTaker = new ScreenShotTaker();
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Inside @BeforeClass hook");
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Inside @AfterClass hook");
		driver.quit();
	}
	
	public static WebDriver getDriver() {
		System.out.println("Inside getDriver()");
		return driver;
	}
}

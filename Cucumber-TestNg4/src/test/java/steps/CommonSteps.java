package steps;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class CommonSteps extends AbstractTestNGCucumberTests {

	private static WebDriver driver;
	
	@BeforeTest
	public void setUp(ITestContext context) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@BeforeSuite
	public void createScreenShotsDir() {
		
		String dirName = "src\\test\\resources\\screenshots\\";
		File folder = new File(dirName);
		
		System.out.println("Inside createScreenShotsDir()");
		
		if(folder.exists()) {
			for(String entry: folder.list()) {
//				System.out.println(entry);
				File file = new File(folder.getAbsolutePath()+"\\"+entry);
				file.delete();
			}
		}
		else {
			folder.mkdir();
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}

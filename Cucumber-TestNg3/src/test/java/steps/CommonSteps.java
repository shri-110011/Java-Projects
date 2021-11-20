package steps;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.cucumber.core.gherkin.FeatureParserException;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.FeatureFileParser;

public class CommonSteps extends AbstractTestNGCucumberTests {
	
	private static WebDriver driver;
	
	private static FeatureFileParser featureFileParser = new FeatureFileParser();

	@BeforeTest
	public void setUp(ITestContext context) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, IOException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		
		Class<?> clss = Class.forName("runners.TestRunner");
		Annotation[] annotations = clss.getAnnotations();
		String[] paths= {};
		for(Annotation annotation: annotations) {
			System.out.println("Flag 1");
			if(annotation.annotationType().getSimpleName().equals("CucumberOptions")) {
				System.out.println("Flag 2");
				CucumberOptions cucumberOptions = (CucumberOptions)annotation;
				paths  = cucumberOptions.features();
				break;
			}
		}
		for(String path: paths) {
			System.out.println("Flag 3");
			System.out.println(path);
			featureFileParser.scanFolderAndAddDataToFeatureFiles(path);
		}
		System.out.println(context.getName());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
}

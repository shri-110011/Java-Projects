package com.shri.automation.steps;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.shri.automation.utilities.ScreenShotTaker;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;

public class CommonSteps extends AbstractTestNGCucumberTests {
	private static WebDriver driver;
	
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
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		if(driver == null) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
		System.out.println(this);
		System.out.println("Inside @BeforeTest hook");
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
//		System.out.println(this);
		driver.quit();
		Thread.sleep(1000);
		System.out.println("Inside @AfterTest hook");
	}
	
	@Before
	public void setScenario(Scenario scenario) throws IOException {
		if(scenario == null) {
			System.out.println("Scenario is null");
		}
		else {
			System.out.println("Scenario.is not null");
		}
		ScreenShotTaker.currentScenario = scenario;
		System.out.println("Inside before method: "+scenario.getName());
	}
	
	public WebDriver getDriver() {
		System.out.println("Inside getDriver()");
		if(driver == null) {
			System.out.println(true);
		}
		else {
			System.out.println(false);
		}
//		System.out.println(this);
		return driver;
	}
}

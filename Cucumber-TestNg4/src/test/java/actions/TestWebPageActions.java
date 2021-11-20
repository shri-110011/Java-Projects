package actions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import locators.TestWebPageLocators;
import steps.CommonSteps;
import utilities.ScreenShotTaker;

public class TestWebPageActions {

	TestWebPageLocators testWebPageLocators;
	WebDriver driver;
	WebDriverWait wait;
	ScreenShotTaker sc = new ScreenShotTaker();
	
	public TestWebPageActions(CommonSteps commonSteps) {
		driver = commonSteps.getDriver();
		this.testWebPageLocators = new TestWebPageLocators(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	};
	
	
	public void verifyTestWebPage() {
		String actualText = testWebPageLocators.tesWebPageHeading.getText();
		
		String expectedText = "Test Webpage";
		
		sc.takeScreenShot(driver, "TestWebpage");
		
		assertEquals(actualText, expectedText);
	}
	
	public void clickOnShowText() throws InterruptedException {
//		scrollSearch(testWebPageLocators.showTextBtn);
//		Thread.sleep(3000);
		sc.takeScreenShot(driver, "ShowText btn");
		testWebPageLocators.showTextBtn.click();
		System.out.println(">> Clicked on Show Text button");
	}
	
	public void scrollSearch(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void verifyParagraph() throws InterruptedException {
//		scrollSearch(testWebPageLocators.paragraph);
		
		//Once providing the implicit wait in the setUp() in CommonSteps.java if we again provide that here then this 
		//implicit wait will override the earlier one.
		
		//An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an 
		//element or elements if they are not immediately available. The default setting is 0, meaning disabled. 
		//Once set, the implicit wait is set for the life of the session.
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		wait.until(ExpectedConditions.visibilityOf(testWebPageLocators.paragraph));
		
		
		Wait<WebDriver> wait = 
				new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("1"));
			}
			
		});
		
		
		if(testWebPageLocators.paragraph.isDisplayed()) {
			System.out.println(testWebPageLocators.paragraph.getText());
			sc.takeScreenShot(driver, "paragraph");
		}
		else {
			Assert.fail("Paragraph not visible");
		}
	}
	
	public void selectFavouriteColor(String favColor) {
		
		Actions a = new Actions(driver);
		a.moveToElement(testWebPageLocators.favColorDropdown);
		a.click();
		if(favColor.equals("Red")) {
			a.sendKeys("Red");
		}
		else if(favColor.equals("Green")) {
			a.sendKeys("Green");
		}
		else if(favColor.equals("Blue")) {
			a.sendKeys("Blue");
		}
		a.build().perform();
		sc.takeScreenShot(driver, "After selecting favourite color");
	}
	
	public void clickOnShowMyFavouriteColorBtn() {
		testWebPageLocators.showFavouriteColotBtn.click();
	}
	
	public void verifyFavouriteColor(String favColor) {
		sc.takeScreenShot(driver, "After clicking on show favourite color button");
		if(testWebPageLocators.favColorPara.getText().equals(favColor)) {
			System.out.println("Displayed favourite color matches the selected favourite color!");
		}
		else {
			Assert.fail("Displayed favourite color doesn't match the selected favourite color!");
		}
	}
	
}

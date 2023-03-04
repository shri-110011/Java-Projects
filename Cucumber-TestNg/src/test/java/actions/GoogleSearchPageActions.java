package actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import locators.GoogleSearchPageLocators;
import steps.CommonSteps;

public class GoogleSearchPageActions {
	
	private GoogleSearchPageLocators googleSearchPageLocators;
	private WebDriver driver;
	
	public GoogleSearchPageActions() {
		googleSearchPageLocators = new GoogleSearchPageLocators();
		driver = CommonSteps.getDriver();
		PageFactory.initElements(driver, googleSearchPageLocators);
	}
	
	public void searchForTheWord(String word) {
		googleSearchPageLocators.searchBox.sendKeys(word, Keys.ENTER);
		System.out.println("Searched for the word: |" + word + "|");
	}
	
	public void shouldDisplaySearchResults() {
		WebElement resultStats = googleSearchPageLocators.resultStats;
		if(resultStats != null) {
			System.out.println("|" + resultStats.getText() + "|");
		}
		else {
			Assert.fail("resultStats is null");
		}
	}
	
	public void clickOnSignInButton() {
		googleSearchPageLocators.signInButton.click();
	}

}

package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import locators.GmailSignInPageLocators;
import steps.CommonSteps;

public class GmailSignInPageActions {

	private WebDriver driver;
	private GmailSignInPageLocators gmailSignInPageLocators;
	
	public GmailSignInPageActions() {
		driver = CommonSteps.getDriver();
		gmailSignInPageLocators = new GmailSignInPageLocators();
		
		PageFactory.initElements(driver, gmailSignInPageLocators);
	}
	
	public void verifyGmailSignInPage() {
		Assert.assertFalse(gmailSignInPageLocators.subHeader == null);
	}
	
}

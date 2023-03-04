package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailSignInPageLocators {

	@FindBy(xpath = "//div[@id='headingSubtext']//span")
	public WebElement subHeader;

}

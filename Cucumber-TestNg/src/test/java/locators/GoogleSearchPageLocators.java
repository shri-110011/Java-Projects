package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPageLocators {

	@FindBy(xpath = "//input[@name='q']")
	public WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='result-stats']")
	public WebElement resultStats;
	
	@FindBy(xpath = "//a[text()='Sign in']")
	public WebElement signInButton;
	
}

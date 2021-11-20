package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestWebPageLocators {

	@FindBy(xpath="/html/body/h2")
	public WebElement tesWebPageHeading;
	
	@FindBy(id="btn1")
	public WebElement showTextBtn;
	
	@FindBy(id="1")
	public WebElement paragraph;
	
	@FindBy(id="color-dropdown")
	public WebElement favColorDropdown;
	
	@FindBy(id="btn2")
	public WebElement showFavouriteColotBtn;
	
	@FindBy(id="fav-color")
	public WebElement favColorPara;
	
	public TestWebPageLocators(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}

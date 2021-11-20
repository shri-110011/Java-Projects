package elements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ebay_Home_Elements {
	
	WebDriver driver;

	@FindBy(linkText = "Advanced") public WebElement advancedLink;
	@FindBy(xpath = "//*[@id=\"gh-ac\"]") public WebElement searchBox;
	@FindBy(xpath = "//*[@id=\"gh-btn\"]") public WebElement searchBtn;
	@FindBy(css = "h1.srp-controls__count-heading > span:first-child") public WebElement noOfItems;
	@FindBy(xpath = "//*[@id=\"gh-cat\"]/option") public List<WebElement> categoriesOptions;
	
	public Ebay_Home_Elements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

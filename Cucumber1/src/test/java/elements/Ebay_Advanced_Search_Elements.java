package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ebay_Advanced_Search_Elements {

	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"gh-la\"]") public WebElement ebayLogo;
	@FindBy(xpath = "//*[@id=\"_nkw\"]") public WebElement searchString;
	@FindBy(xpath = "//*[@id=\"_ex_kw\"]") public WebElement excludeString;
	@FindBy(xpath = "//*[@id=\"adv_search_from\"]/fieldset[3]/input[2]") public WebElement minPrice;
	@FindBy(xpath = "//*[@id=\"adv_search_from\"]/fieldset[3]/input[3]") public WebElement maxPrice;
	@FindBy(xpath = "//*[@id=\"searchBtnLowerLnk\"]") public WebElement searchBtn;
	
	public Ebay_Advanced_Search_Elements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}

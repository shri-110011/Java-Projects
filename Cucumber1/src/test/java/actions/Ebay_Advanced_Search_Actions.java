package actions;

import org.openqa.selenium.WebDriver;

import elements.Ebay_Advanced_Search_Elements;
import steps.Common_Steps;

public class Ebay_Advanced_Search_Actions {
	private WebDriver driver;
	Ebay_Advanced_Search_Elements ebay_Advanced_Search_Elements;
	
	public Ebay_Advanced_Search_Actions(Common_Steps common_Steps) {
		this.driver = common_Steps.getDriver();
		ebay_Advanced_Search_Elements = new Ebay_Advanced_Search_Elements(driver);
	}
	
	public void clickOnEbayLogo() {
		ebay_Advanced_Search_Elements.ebayLogo.click();
	}
	
	public void enterSearchString(String str) {
		ebay_Advanced_Search_Elements.searchString.sendKeys(str);
	}
	
	public void enterExcludeString(String str) {
		ebay_Advanced_Search_Elements.excludeString.sendKeys(str);
	}
	
	public void enterMinPrice(String str) {
		ebay_Advanced_Search_Elements.minPrice.sendKeys(str);
	}
	
	public void enterMaxPrice(String str) {
		ebay_Advanced_Search_Elements.maxPrice.sendKeys(str);
	}
	
	public void clickSearchBtn() {
		ebay_Advanced_Search_Elements.searchBtn.click();
	}

}

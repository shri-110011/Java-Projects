package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import elements.Ebay_Home_Elements;
import steps.Common_Steps;

public class Ebay_Home_Actions {
	private WebDriver driver;
	Ebay_Home_Elements ebay_Home_Elements;
	
	public Ebay_Home_Actions(Common_Steps common_Steps) {
		this.driver = common_Steps.getDriver();
		ebay_Home_Elements = new Ebay_Home_Elements(driver);
	}
	
	public void clickAdvancedLink() {
		ebay_Home_Elements.advancedLink.click();
	}
	
	public void searchAnItem(String item) {
		ebay_Home_Elements.searchBox.sendKeys(item);
	}
	
	public void clickSearchBtn() {
		ebay_Home_Elements.searchBtn.click();
	}
	
	public int getSearchItemCount() {
		String itemCount = ebay_Home_Elements.noOfItems.getText().trim().replace(",", "");
		int itemCountVal = Integer.parseInt(itemCount);
		return itemCountVal;
	}
	
	public void selectCategoryOptions(String option) {
		List<WebElement> categories = ebay_Home_Elements.categoriesOptions;
		
		for(WebElement category: categories) {
			if(category.getText().trim().toLowerCase().equals(option.toLowerCase())) {
				category.click();
				break;
			}
		}
	}
	
	public void clickOnLinkByText(String text) {
		driver.findElement(By.linkText(text)).click();
	}

}

package steps;

import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ebay_Home_Steps {
	WebDriver driver;
	
	public Ebay_Home_Steps(Common_Steps common_steps) {
		this.driver = common_steps.getDriver();
	}
	
	@Given("I am on Ebay Home Page")
	public void i_am_on_Ebay_Home_Page() {
		
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://www.ebay.com/");
//	    System.out.println("I am on Ebay Home Page");
		
	}

	@When("I click on Advanced search link")
	public void i_click_on_Advanced_search_link() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.linkText("Advanced")).click();;
//		 System.out.println("I click on Advanced search link");
//		 throw new Error("asd");
	}

	@Then("I navigate to Advanced Search Page")
	public void i_navigate_to_Advanced_Search_Page() {
	    // Write code here that turns the phrase above into concrete actions
		String expUrl = "https://www.ebay.com/sch/ebayadvsearch";
		String actUrl = driver.getCurrentUrl();
		if(!actUrl.equals(expUrl)) {
			fail("Can't navigate to the expected url");
		}
		driver.quit();
//		 System.out.println("I navigate to Advanced Search Page");
	}
	
	@When("I search for {string}")
	public void i_search_for_iphone(String str) {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys(str);
	    driver.findElement(By.xpath("//*[@id=\"gh-btn\"]")).click();
	}

	@Then("I get search results greater than {int}")
	public void i_get_search_results_greater_than(int count) {
	    // Write code here that turns the phrase above into concrete actions
	    String itemCountTxt = driver.findElement(By.cssSelector("h1.srp-controls__count-heading > span:first-child")).getText();
	    itemCountTxt.replace(",", "");
	    int itemCount = Integer.parseInt(itemCountTxt);
	    if(itemCount <= count ) {
	    	fail("Item count is not greater than 80");
	    }
	}	
	
	@When("I search for {string} under {string} category")
	public void i_search_for_under_category(String str1, String str2) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys(str1);
		List<WebElement> categories = driver.findElements(By.xpath("//*[@id=\"gh-cat\"]/option"));
		for(WebElement category : categories) {
			if(category.getText().trim().toLowerCase().equals(str2.toLowerCase())) {
				category.click();
				break;
			}
		}	
		driver.findElement(By.xpath("//*[@id=\"gh-btn\"]")).click();
		Thread.sleep(3000);
	}
	
	@When("I click on {string}")
	public void i_click_on(String link) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.linkText(link)).click();
	    Thread.sleep(1000);
	}

	@Then("I verify that the page navigate to {string} and title contains {string}")
	public void i_verify_that_the_page_navigate_to_and_title_contains(String url, String title) {
	    // Write code here that turns the phrase above into concrete actions
	   String actUrl = driver.getCurrentUrl();
	   String actTitle = driver.getTitle();
	   if(!actUrl.equals(url)) {
		   fail("Page doesn't navigate to the expected url i.e. "+ url);
	   }
	   if(!actTitle.equals(title)) {
		   fail("Title mismatch");
	   }
	}

}

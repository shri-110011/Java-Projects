package steps;

import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.Common_Actions;
import actions.Ebay_Home_Actions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ebay_Home_Steps {
	Common_Actions common_Actions;
	Ebay_Home_Actions ebay_Home_Actions;
	
	public Ebay_Home_Steps(Common_Actions common_Actions, Ebay_Home_Actions ebay_Home_Actions) {
		this.common_Actions = common_Actions;
		this.ebay_Home_Actions = ebay_Home_Actions;
	}
	
	@Given("I am on Ebay Home Page")
	public void i_am_on_Ebay_Home_Page() {
	    // Write code here that turns the phrase above into concrete actions
		common_Actions.goToUrl("https://www.ebay.com/");
//	    System.out.println("I am on Ebay Home Page");
		
	}

	@When("I click on Advanced search link")
	public void i_click_on_Advanced_search_link() {
	    // Write code here that turns the phrase above into concrete actions
		ebay_Home_Actions.clickAdvancedLink();
	}

	@Then("I navigate to Advanced Search Page")
	public void i_navigate_to_Advanced_Search_Page() {
	    // Write code here that turns the phrase above into concrete actions
		String expUrl = "https://www.ebay.com/sch/ebayadvsearch";
		String actUrl = common_Actions.getCurrentPageUrl();
		if(!actUrl.equals(expUrl)) {
			fail("Can't navigate to the expected url");
		}
		common_Actions.quitWebDriver();
//		 System.out.println("I navigate to Advanced Search Page");
	}
	
	@When("I search for {string}")
	public void i_search_for_iphone(String str) {
	    // Write code here that turns the phrase above into concrete actions
	    ebay_Home_Actions.searchAnItem(str);
	    ebay_Home_Actions.clickSearchBtn();
	}

	@Then("I get search results greater than {int}")
	public void i_get_search_results_greater_than(int count) {
	    // Write code here that turns the phrase above into concrete actions
	    int itemCount = ebay_Home_Actions.getSearchItemCount();
	    if(itemCount <= count ) {
	    	fail("Item count is not greater than 80");
	    }
	}	
	
	@When("I search for {string} under {string} category")
	public void i_search_for_under_category(String str1, String str2) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		ebay_Home_Actions.searchAnItem(str1);
		ebay_Home_Actions.selectCategoryOptions(str2);
		ebay_Home_Actions.clickSearchBtn();
		Thread.sleep(3000);
	}
	
	@When("I click on {string}")
	public void i_click_on(String link) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    ebay_Home_Actions.clickOnLinkByText(link);
	    Thread.sleep(1000);
	}

	@Then("I verify that the page navigate to {string} and title contains {string}")
	public void i_verify_that_the_page_navigate_to_and_title_contains(String url, String title) {
	    // Write code here that turns the phrase above into concrete actions
	   String actUrl = common_Actions.getCurrentPageUrl();
	   String actTitle = common_Actions.getCurrentPageTitle();
	   if(!actUrl.equals(url)) {
		   fail("Page doesn't navigate to the expected url i.e. "+ url);
	   }
	   if(!actTitle.equals(title)) {
		   fail("Title mismatch");
	   }
	}

}

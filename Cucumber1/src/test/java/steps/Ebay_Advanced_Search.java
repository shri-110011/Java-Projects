package steps;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import actions.Common_Actions;
import actions.Ebay_Advanced_Search_Actions;
import actions.Ebay_Home_Actions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ebay_Advanced_Search {
	
	Common_Actions common_Actions;
	Ebay_Advanced_Search_Actions ebay_Advanced_Search_Actions;
	
	public Ebay_Advanced_Search(Common_Actions common_Actions, Ebay_Advanced_Search_Actions ebay_Advanced_Search_Actions) {
		this.common_Actions = common_Actions;
		this.ebay_Advanced_Search_Actions = ebay_Advanced_Search_Actions;
	}
	
	@Given("I am on Ebay Advanced Search Page")
	public void i_am_on_Ebay_Advanced_Search_Page() {
	    common_Actions.goToUrl("https://www.ebay.com/sch/ebayadvsearch");
	}

	@When("I click on Ebay Logo")
	public void i_click_on_Ebay_Logo() {
		ebay_Advanced_Search_Actions.clickOnEbayLogo();
	    
	}

	@Then("I am navigated back to Ebay Home Page")
	public void i_am_navigated_back_to_Ebay_Home_Page() {
		String expUrl = "https://www.ebay.com/";
		String actUrl = common_Actions.getCurrentPageUrl();
		if(!actUrl.equals(expUrl)) {
			fail("Can't navigate to the expected url");
		}
		common_Actions.quitWebDriver();
	}
	
	@When("I advance search an item")
	public void i_advance_search_an_item(DataTable dataTable) throws InterruptedException {
		ebay_Advanced_Search_Actions.enterSearchString(dataTable.cell(1, 0));
		ebay_Advanced_Search_Actions.enterExcludeString(dataTable.cell(1, 1));
		ebay_Advanced_Search_Actions.enterMinPrice(dataTable.cell(1, 2));
		ebay_Advanced_Search_Actions.enterMaxPrice(dataTable.cell(1, 3));
	   	ebay_Advanced_Search_Actions.clickSearchBtn();
	 	Thread.sleep(3000);
	}

}

package steps;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ebay_Advanced_Search {
	
	WebDriver driver;
	
	public Ebay_Advanced_Search(Common_Steps common_steps) {
		this.driver = common_steps.getDriver();
		System.out.println("@@@@@@@@@@@@2");
	}
	
	@Given("I am on Ebay Advanced Search Page")
	public void i_am_on_Ebay_Advanced_Search_Page() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.get("https://www.ebay.com/sch/ebayadvsearch");
	}

	@When("I click on Ebay Logo")
	public void i_click_on_Ebay_Logo() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id=\"gh-la\"]")).click();
	    
	}

	@Then("I am navigated back to Ebay Home Page")
	public void i_am_navigated_back_to_Ebay_Home_Page() {
	    // Write code here that turns the phrase above into concrete actions
		String expUrl = "https://www.ebay.com/";
		String actUrl = driver.getCurrentUrl();
		if(!actUrl.equals(expUrl)) {
			fail("Can't navigate to the expected url");
		}
		driver.quit();
	}
	
	@When("I advance search an item")
	public void i_advance_search_an_item(DataTable dataTable) throws InterruptedException {
	   	driver.findElement(By.xpath("//*[@id=\"_nkw\"]")).sendKeys(dataTable.cell(1, 0));
	   	driver.findElement(By.xpath("//*[@id=\"_ex_kw\"]")).sendKeys(dataTable.cell(1, 1));
	   	driver.findElement(By.xpath("//*[@id=\"adv_search_from\"]/fieldset[3]/input[2]")).sendKeys(dataTable.cell(1, 2));
	 	driver.findElement(By.xpath("//*[@id=\"adv_search_from\"]/fieldset[3]/input[3]")).sendKeys(dataTable.cell(1, 3));
	 	driver.findElement(By.xpath("//*[@id=\"searchBtnLowerLnk\"]")).click();
	 	Thread.sleep(3000);
	}

}

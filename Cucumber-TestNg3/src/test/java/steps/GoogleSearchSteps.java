package steps;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchSteps {
	WebDriver driver;
	
	public GoogleSearchSteps() {
		CommonSteps commonSteps = new CommonSteps();
		driver = commonSteps.getDriver();
	}
			
	@Given("I am on the Google Search Page {string}")
	public void i_am_on_the_google_search_page(String url) throws Exception {
		if(driver==null) {
			System.out.println("driver is null");
		}
		else {
			System.out.println("driver is not null");
		}
		System.out.println("url: "+url);
	    driver.get(url);
	    Thread.sleep(5000);
	}

	@When("I enter the {string}")
	public void i_enter_the(String searchText) {
	    WebElement searchBar = driver.findElement(By.xpath("//input[@title=\"Search\"]"));
	    searchBar.sendKeys(searchText);
	    searchBar.sendKeys(Keys.ENTER);
	}

	@Then("I verify I get search results greater than {string}")
	public void i_verify_i_get_search_results_greater_than(String count) {
		WebElement resultStats = driver.findElement(By.xpath("//div[@id=\"result-stats\"]"));
		int resultCount = Integer.parseInt(String.join("", resultStats.getText().split(" ")[1].split(",")));
		System.out.println(resultCount);
		assertTrue(resultCount>Integer.parseInt(count), "Search result is not greater than: "+count);
	}

}

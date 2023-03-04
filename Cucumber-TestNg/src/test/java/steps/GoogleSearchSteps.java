package steps;

import org.openqa.selenium.WebDriver;

import actions.GoogleSearchPageActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchSteps {
	private WebDriver driver;
	private GoogleSearchPageActions googleSearchPageActions;
	
	
	public GoogleSearchSteps( ) {
		System.out.println("Inside GoogleSearchPageSteps() ");
		driver = CommonSteps.getDriver();
		googleSearchPageActions = new GoogleSearchPageActions();
	}
	
	@Given("I am on the google search url {string}")
	public void i_am_on_the_google_search_url(String url) {
	  driver.get(url);
	}

	@When("I search for the word {string}")
	public void i_search_for_the_word(String word) {
		googleSearchPageActions.searchForTheWord(word);
	}

	@Then("I should get some results")
	public void i_should_get_some_results() {
	    googleSearchPageActions.shouldDisplaySearchResults();
	}
}

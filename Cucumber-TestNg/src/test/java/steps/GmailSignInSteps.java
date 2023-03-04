package steps;

import actions.GmailSignInPageActions;
import actions.GoogleSearchPageActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GmailSignInSteps {
	
	private GoogleSearchPageActions googleSearchPageActions;
	private GmailSignInPageActions gmailSignInPageActions;
	
	public GmailSignInSteps() {
		googleSearchPageActions = new GoogleSearchPageActions();
		gmailSignInPageActions = new GmailSignInPageActions();
	}

	@When("I click on Sign in button")
	public void i_click_on_sign_in_button() {
	   googleSearchPageActions.clickOnSignInButton();
	}

	@Then("I should see the Gmail Sign in page")
	public void i_should_see_the_sign_in_page() {
		gmailSignInPageActions.verifyGmailSignInPage();
	}
	
}

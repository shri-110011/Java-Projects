package steps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import actions.TestWebPageActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestWebPageSteps {
	
	WebDriver driver;
	TestWebPageActions testWebPageActions;
	
	public TestWebPageSteps(CommonSteps commonSteps) {
		driver = commonSteps.getDriver();
		testWebPageActions = new TestWebPageActions(commonSteps);
	};

	@Given("I enter the url {string}")
	public void i_enter_the_url(String url) {
	    driver.get(url);
	}

	@Given("I land on the Test Webpage")
	public void i_land_on_the_test_webpage() {
	   testWebPageActions.verifyTestWebPage();
	}

	@When("I scroll down to find the Show Text button and click on it")
	public void i_scroll_down_to_find_the_show_text_button() throws InterruptedException {
		testWebPageActions.clickOnShowText();
	}
	
	@Then("the paragraph should be displayed")
	public void the_paragraph_should_be_displayed() throws InterruptedException {
	    testWebPageActions.verifyParagraph();
	}
	
	@When("I select favourite color as {string}")
	public void i_select_favourite_color_as(String favColor) {
		testWebPageActions.selectFavouriteColor(favColor);
	}

	@When("I click on the show favourite color button")
	public void i_click_on_the_show_favourite_color_button() {
		testWebPageActions.clickOnShowMyFavouriteColorBtn();
	}

	@Then("My favourite color should be displayed {string}")
	public void my_favourite_color_should_be_displayed(String favColor) {
	  testWebPageActions.verifyFavouriteColor(favColor);
	}

	
	
}

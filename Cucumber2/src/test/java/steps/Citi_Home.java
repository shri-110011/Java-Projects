package steps;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Citi_Home {
	private WebDriver driver;
	
	public Citi_Home(Common_Steps common_Steps) {
		this.driver = common_Steps.getDriver();
	}
	
	@Given("I am on Citi home page")
	public void i_am_on_Citi_home_page() throws InterruptedException {
	    driver.get("https://uat02.citi.com/");
//	    Thread.sleep(2000);
	}

	@When("I click on sign on")
	public void i_click_on_sign_on() {
	    driver.findElement(By.linkText("Sign On")).click();
	}

	@Then("I am navigated to {string}")
	public void i_am_navigated_to(String string) {
	    String actUrl = driver.getCurrentUrl();
	    String expectedUrl = "https://uat02.citi.com/login";
	    
	    if(!actUrl.equals(expectedUrl)) {
	    	fail("The url does not match");
	    }
	}


}

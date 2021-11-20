package steps;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.shri.automation.steps.CommonSteps;
import com.shri.automation.utilities.ScreenShotTaker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Test
public class GithubLoginPageSteps {
	private WebDriver driver;
	ScreenShotTaker screenShotTaker = new ScreenShotTaker();

	public GithubLoginPageSteps() {
		System.out.println("Inside CitiHomePageSteps() ");
		CommonSteps commonSteps = new CommonSteps();
		this.driver = commonSteps.getDriver();
		System.out.println("########################################");
	}

	@Given("I am on the Github login page whose url is {string}")
	public void i_am_on_the(String githubHomePageUrl) throws InterruptedException, IOException {
//		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
//		driver = new ChromeDriver();
//		System.out.println("########################################");
		System.out.println(githubHomePageUrl);
		driver.get(githubHomePageUrl);
		driver.manage().window().maximize();
		screenShotTaker.takeScreenShot(driver, "GithubLoginPage");
		Thread.sleep(2000);
	}

	@When("the user enters the right {string} and {string}")
	public void the_user_enters_the_right_and(String username, String pwd) throws IOException {
		driver.findElement(By.xpath("//*[@id=\"login_field\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"login_field\"]")).sendKeys(username);

		driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pwd);
		screenShotTaker.takeScreenShot(driver, "GithubSiginingIn");
	}

	@When("the user clicks on the sign on button")
	public void the_user_clicks_on_the_sign_on_button() throws InterruptedException {
		Reporter.log("Account name 123###########!");
		driver.findElement(By.xpath("//*[@id=\"login\"]/div[4]/form/div/input[12]")).click();
		Reporter.log("Account name doesn't match!");
		Thread.sleep(000);
	}

	@Then("the user is navigated to the {string}")
	public void the_user_is_navigated_to_the(String githubDashboardUrl) {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = githubDashboardUrl;
		System.out.println("Actual url is:" + actualUrl + " " + actualUrl.length());
		System.out.println("Expected url is:" + expectedUrl + " " + expectedUrl.length());
		if (!actualUrl.equals(expectedUrl)) {
//				final byte[] scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//				scenario.attach(scr, "image/png", scenario.getName());
			// Convert web driver object to TakeScreenshot
//			screenShotTaker.takeScreenShot(driver, "GithubLoginError");
			fail("Dashboard url didn't match the expected url:" + expectedUrl);
		}
	}

	@Given("I am on the Github dashboard page whose url is {string}")
	public void i_am_on_the_github_dashboard_page_whose_url_is(String githubDashboardUrl) throws InterruptedException {
		driver.get(githubDashboardUrl);
//	   Thread.sleep(2000);
	}

	@Then("I see that the account name is {string}")
	public void i_see_that_the_account_name_is(String accountName) {
		driver.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/summary/img")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/header/div[7]/details/details-menu/div[1]/a/strong")));
		String actualAccountName = element.getText();
		String expectedAccountName = accountName;
		if (!accountName.equals(expectedAccountName)) {
			System.out.println("Expected account name: " + expectedAccountName);
			System.out.println("Actual account name: " + actualAccountName);
//			screenShotTaker.takeScreenShot(driver, "AccountNameNotMatching");
			fail("Account name doesn't match!");
		}
	}
	
}

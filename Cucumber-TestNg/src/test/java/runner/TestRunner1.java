package runner;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import steps.CommonSteps;

@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = "steps",
		plugin = {"pretty", "json:target/json-report/cucumber1.json", "html:target/cucumber-reports/report1.html"},
		dryRun = false,
		monochrome = true,
		tags = "@GmailLogin"
)

@Test
public class TestRunner1 extends CommonSteps {

}

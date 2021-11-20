package runner;

import org.testng.annotations.Test;

import com.shri.automation.steps.CommonSteps;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"steps", "com.shri.automation.steps"},
		plugin = {"pretty:target/pretty", "json:target/cucumber-reports/cucumber.json"},
		dryRun = false,
		monochrome = true,
		tags="@demo"
)

@Test
public class TestRunner extends CommonSteps {

}

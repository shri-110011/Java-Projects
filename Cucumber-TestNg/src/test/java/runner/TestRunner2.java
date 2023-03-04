package runner;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import steps.CommonSteps;

@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = "steps",
		plugin = {"pretty", "json:target/json-report/cucumber2.json", "html:target/cucumber-reports/reports2.html"},
		dryRun = false,
		monochrome = true,
		tags = "@GoogleSearch"
)

@Test
public class TestRunner2 extends CommonSteps {

}
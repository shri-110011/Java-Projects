package runners;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import steps.CommonSteps;

@CucumberOptions(
		features = {"src/test/resources/Features"},
		glue = {"steps"},
		plugin = {"pretty", "json:target/json-report/cucumber.json"},
		dryRun = false,
		monochrome = true
)

@Test
public class TestRunner extends CommonSteps {
	
}

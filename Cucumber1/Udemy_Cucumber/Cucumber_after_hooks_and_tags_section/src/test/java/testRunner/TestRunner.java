package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
			features = {"features"},
			glue = {"steps"},
			plugin = {"pretty", "html:Report4"},
			dryRun = false,
			strict = true,
			monochrome = true,
//			tags = {"@P6"}//Only one tag can be run at a time
			tags = "@P1 and @P3"//This is the latest syntax for cucumber options tag
//			name = {"Logo"}
		)
public class TestRunner {

}

package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "StepDefinitions",
        plugin = {"pretty", "html:target/jsonReports/cucumber-reports.html", "json:target/jsonReports/cucumber.json"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

}

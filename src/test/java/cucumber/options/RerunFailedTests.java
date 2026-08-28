package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",   // rerun only failed scenarios
        glue = {"stepDefinition"},
        tags = "@testone",                // rerun only failed @testone scenarios
        plugin = {
                "pretty",
                "html:target/rerun-failed.html"
        },
        monochrome = true
)
public class RerunFailedTests extends AbstractTestNGCucumberTests {
}

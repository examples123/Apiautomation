package cucumber.options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java",
        glue = {"stepDefinition"},
        tags = "@testone",   // 🔖 only run scenarios tagged @testone
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "rerun:target/rerun.txt"   // rerun plugin logs failed scenarios
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {}

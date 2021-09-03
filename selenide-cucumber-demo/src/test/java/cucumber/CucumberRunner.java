package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"cucumber.stepDefinitions", "cucumber.hooks"},
        plugin = {"pretty", "html:reports/cucumber-report.html",
//                "junit:reports/Cucumber.xml",
//                "json:reports/Cucumber.json"
        },
        features = {"src/test/resources/features"},
        monochrome =true,
        publish = true
        )

public class CucumberRunner {
}
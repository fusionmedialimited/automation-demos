import com.codeborne.selenide.testng.TextReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


@CucumberOptions(
        glue = {"cucumber.stepDefinitions", "cucumber.hooks"},
        plugin = {
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                "progress",
                "summary"

//                "pretty", "html:reports/cucumber-report.html",
//                "junit:reports/Cucumber.xml",
//                "json:reports/Cucumber.json"
        },
        features = {"src/test/resources/features"},
        monochrome =false,
        publish = false
        //tags = "@REFA-2711"
)

//@Listeners({TextReport.class})
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
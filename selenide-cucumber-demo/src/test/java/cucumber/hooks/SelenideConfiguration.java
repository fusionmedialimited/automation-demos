package cucumber.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;

import static java.lang.Boolean.parseBoolean;


@Listeners({TextReport.class})
public class SelenideConfiguration {
    public void getBrowser(){
        String browser = System.getProperty("selenide.browser", "chrome");
        boolean headless = parseBoolean(System.getProperty("selenide.headless", "false"));

        Configuration.browser = browser;
        Configuration.headless = headless;
        Configuration.startMaximized = true;
        Configuration.screenshots = true;
        Configuration.pageLoadTimeout = 300000;
        Configuration.savePageSource =false;
        Configuration.reportsFolder = "reports";

        Configuration.baseUrl = "https://www.investing.com/";

    }

    public void closeSession(){
        WebDriverRunner.closeWebDriver();
    }
}

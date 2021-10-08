package cucumber.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.TextReport;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;

import java.io.FileOutputStream;
import java.util.Properties;

//import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.qameta.allure.Allure.step;
import static java.lang.Boolean.parseBoolean;


//@Listeners({TextReport.class})
public class SelenideConfiguration {
    public void getBrowser(){
        String browser = System.getProperty("selenide.browser", "chrome");
        boolean headless = parseBoolean(System.getProperty("selenide.headless", "false"));
        String host = "https://www.investing.com/";

        Configuration.browser = browser;

        if (headless) {
            Configuration.startMaximized = false;
            Configuration.browserSize = "1920x1080";
        } else {
            Configuration.startMaximized = true;
        }

        Configuration.screenshots = true;
        Configuration.pageLoadTimeout = 60000;
        Configuration.savePageSource =false;
        Configuration.reportsFolder = "reports";

        Configuration.baseUrl = host;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        step("Launch the " + browser.substring(0, 1).toUpperCase() + browser.substring(1)
                + " browser and open the " + host + " site", () ->
                Selenide.open(host)
        );

        // write Allure's environment.xml to have environment widget in the report
        Capabilities cap = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();

        String path = "target/allure-results";

        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Browser", browser);
            props.setProperty("Browser.Version", cap.getVersion());
            props.setProperty("Browser Headless mode", String.valueOf(headless));
            props.setProperty("HOST", host);
            props.setProperty("Edition", "WWW");

            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();

        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }

    }


    public void closeSession(){
        step("Closing WebDriver", () -> {
                    SelenideLogger.removeListener("AllureSelenide");
                    WebDriverRunner.closeWebDriver();
                }
            );
    }
}

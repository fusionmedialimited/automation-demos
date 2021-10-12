package Infrastructure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.TextReport;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static Infrastructure.AllureAttachments.addVideo;
import static Infrastructure.AllureAttachments.attachBrowserConsoleLogs;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.qameta.allure.Allure.step;
import static java.lang.Boolean.parseBoolean;
import static java.lang.System.setProperty;


@Listeners({TextReport.class})
public class TestBase {

    public String browser = System.getProperty("selenide.browser", "chrome");
    boolean headless = parseBoolean(System.getProperty("selenide.headless", "false"));

    @BeforeSuite
    public void launchBrowserAndOpenSite() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("94.0");
        Configuration.browserCapabilities = capabilities;


        Configuration.remote = "http://172.16.0.175:4444/wd/hub/";
        Configuration.browser = browser;

        String host = "https://www.investing.com";

        Configuration.holdBrowserOpen = false;

        Configuration.fastSetValue = true;
        Configuration.baseUrl = host;
        Configuration.headless = headless;

        if (headless) {
            Configuration.startMaximized = false;
            Configuration.browserSize = "1920x1080";
        } else {
            Configuration.startMaximized = true;
        }

        Configuration.pageLoadTimeout = 60000; // wait for the page to load up to 1 min
        Configuration.reportsFolder = "build/reports";

        setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        step("Launch the " + browser.substring(0, 1).toUpperCase() + browser.substring(1)
                + " browser and open the " + host + " site", () ->
                Selenide.open(host)
        );

        Capabilities cap = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browser)
                        .put("Browser.Version", cap.getVersion())
                        .put("Browser Headless mode", String.valueOf(headless))
                        .put("HOST", host)
                        .put("OS", System.getProperty("os.name").toLowerCase())
                        .build(), System.getProperty("user.dir")
                        + "/build/allure-results/");
    }

    @AfterTest
    public void afterTest() {
        if (browser.equals("chrome"))
            attachBrowserConsoleLogs();

        addVideo();
    }

    @AfterSuite
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        WebDriverRunner.closeWebDriver();
    }

}

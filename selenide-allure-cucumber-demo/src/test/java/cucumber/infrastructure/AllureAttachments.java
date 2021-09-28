package cucumber.infrastructure;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class AllureAttachments {
    public static final Logger LOGGER = LoggerFactory.getLogger(AllureAttachments.class);

    /** This method attaches browser's console (at least for Chrome) logs to Allure report */
    public static void attachBrowserConsoleLogs() {
        attachAsText("Browser console logs: ", getConsoleLogs());
    }

    /** This method attaches a text as a new step to Allure report */
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String addMessage(String attachName, String text) {
        return text;
    }

    /** This method attaches a text as a field to Allure report. The text will appear in the console out too*/
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        Reporter.log("[Attachment] " + attachName + ": " + message + "\n", true);
        return message;
    }

    /** This method attaches a screenshot of the current web page to Allure report */
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] attachScreenshot(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /** This method attaches the current web page to Allure report */
    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] attachPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    /** This method returns the browser's console logs (works for Chrome) */
    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

}


package Infrastructure;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class AllureAttachments {
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

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(getSessionId())
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl(String sessionId) {
        String videoUrl = "http://localhost:4444/video/" + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionId(){
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }


}

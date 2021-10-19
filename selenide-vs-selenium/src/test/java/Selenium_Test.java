import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Selenium_Test {

    @Test
    public void selenium_test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.investing.com/");

        try {
            if (driver.findElement(By.cssSelector("#PromoteSignUpPopUp")).isDisplayed()) {
                driver.findElement(By.cssSelector(".largeBannerCloser")).click();
                Assertions.assertFalse(driver.findElement(By.cssSelector("#PromoteSignUpPopUp")).isDisplayed());
            }
        } catch (NoSuchElementException var9) {
            System.out.println("The promotion pop-up wasn't shown, go on as usual");
        }

        new WebDriverWait(driver, Duration.ofSeconds(5L).toMillis())
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#userAccount"))));

        WebElement signInSection = driver.findElement(By.cssSelector("#userAccount"));
        By signInLink = By.cssSelector(".login");

        assertEquals(signInSection.findElement(signInLink).getText(), "Sign In");
        signInSection.findElement(signInLink).click();

        new WebDriverWait(driver, Duration.ofSeconds(5L).toMillis())
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#loginPopup"))));

        WebElement signInDialog = driver.findElement(By.cssSelector("#loginPopup"));
        By loginField = By.cssSelector("#loginFormUser_email");

        assertEquals(driver.findElement(loginField).getAttribute("placeholder"), "Email");
        driver.findElement(loginField).sendKeys("qa_automation@investing.com");

        By passwordField = By.cssSelector("#loginForm_password");
        assertEquals(driver.findElement(passwordField).getAttribute("placeholder"), "Password");
        driver.findElement(passwordField).sendKeys("Aa111111");

        WebElement signInButton = driver.findElement(By.cssSelector("[onclick='loginFunctions.submitLogin();']"));
        assertEquals(signInButton.getText(), "Sign In");
        assertEquals(signInButton.getCssValue("background-color"), "rgba(255, 165, 0, 1)");

        signInButton.click();

        By userTitle = By.cssSelector(".myAccount.topBarText");

        new WebDriverWait(driver, Duration.ofSeconds(5L).toMillis())
                .until(ExpectedConditions.visibilityOf(driver.findElement(userTitle)));

        assertEquals(driver.findElement(userTitle).getText(), "QA");

        driver.close();
    }
}
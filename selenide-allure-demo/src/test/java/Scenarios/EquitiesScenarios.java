package Scenarios;

import Infrastructure.TestBase;
import PageObject.EquitiesInstrumentPage;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.TextReport;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static Infrastructure.AllureAttachments.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.*;

@Listeners({TextReport.class})
public class EquitiesScenarios extends TestBase {

    EquitiesInstrumentPage equitiesPage = new EquitiesInstrumentPage();

    @Epic("Regression")
    @Story("I want to verify that the current price value for the GameStop Corp on the page is within " +
            "the values of the the 52 Week Range from the page")
    @Feature("Verify that the current GameStop Corp price value is within the 52 week range")
    @Test(description = "[Scenario]: Compare the current price value with the 52 week range.")
    public void valueIsWithin52wkRange() {

        step("[Given] The GameStop Corp page", () -> {
            equitiesPage.goToSpecificEquitiesPage("/gamestop-corp");
            attachScreenshot("GameStop Corp page");
        });

        double currentPrice = step("[When] Get the current price value from the page", () -> {
            attachScreenshot("the page with current price value");

            double value =  Double.parseDouble(equitiesPage.currentPrice
                    .shouldBe(visible)
                    .getText()
            );

            attachAsText("The current price value is: ", String.valueOf(value));

            return value;
        });

        String weekRange = step("[And] Get the 52 week range values", () -> {

            String value = equitiesPage.fiftyTwoWeekRange
                    .scrollTo()
                    .shouldBe(visible)
                    .getText();

            attachScreenshot("52 week range values");
            attachAsText("52 week range values: ", value);

            return value;
        });

        step("[Then] Verify that the current value is within the 52 week range values", () -> {
            String[] values = weekRange.split("-");

            double startWeeksValue = Double.parseDouble(values[0]);
            double endWeeksValue = Double.parseDouble(values[1]);

            assertTrue(startWeeksValue < currentPrice && currentPrice < endWeeksValue,
                    "The currentPrice value: " + currentPrice + " is not within the expected range: " + weekRange);
        });

        attachBrowserConsoleLogs();
    }


    @DataProvider(name = "equitiesURI")
    public static Object[][] equitiesURI() {
        return new Object[][]{
                {"/apple-computer-inc"},
                {"/google-inc"}
        };
    }

    @Epic("Regression")
    @Story("I want to verify elements existence across a sample of 2 equities")
    @Feature("Equities: Element Existence")
    @Test(description = "[Scenario]: Check Equity Page Elements.",
            dataProvider = "equitiesURI")
    public void equitiesPageElementsVerification(String URI) {
        step("[Given] a signed-out user", () -> {

            if (getWebDriver().getCurrentUrl().matches(".*.investing.com/")) {
                $(".topBarTools .login.bold").shouldBe(visible, Duration.ofSeconds(6));
            } else {
                equitiesPage.signInBtn.shouldBe(visible, Duration.ofSeconds(6));
            }
        });

        step("[When] user navigates to the equity " + URI, () -> {
            equitiesPage.goToSpecificEquitiesPage(URI);
            attachScreenshot("the " + URI + " equity page");
        });

        step("[Then] equity title is displayed", () ->
                equitiesPage.title.shouldBe(visible)
        );

        step("[And] equity price is displayed", () ->
                equitiesPage.currentPrice.shouldBe(visible)
        );

        step("[And] equity graph is displayed", () ->
                equitiesPage.graph.shouldBe(visible)
        );

        step("[And] equity news and analysis section is displayed", () -> {
                    equitiesPage.newsSection.shouldBe(visible);
                    equitiesPage.analysisSection.shouldBe(visible);
        });

        step("[And] equity three tables are displayed and have content", () -> {
            equitiesPage.technicalSummeryTbl.shouldBe(visible, Duration.ofSeconds(5));
            equitiesPage.trendingStocksTbl.shouldBe(visible, Duration.ofSeconds(5));
            equitiesPage.companyProfileBlock.shouldBe(visible, Duration.ofSeconds(5));
            equitiesPage.companyProfileTable.shouldBe(visible, Duration.ofSeconds(5));
            equitiesPage.companyProfileDescription.shouldBe(visible, Duration.ofSeconds(5));
        });
    }


}

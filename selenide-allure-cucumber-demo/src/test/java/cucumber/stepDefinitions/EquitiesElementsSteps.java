package cucumber.stepDefinitions;

import com.codeborne.selenide.testng.annotations.Report;
import cucumber.pages.EquityInstrumentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

//@Report
public class EquitiesElementsSteps {
    //@Inject
    EquityInstrumentPage equityInstrumentPage = new EquityInstrumentPage();

//    @Given("a signed-out user")
//    public void aSignedOutUser() {
//        open("");
//        //equityInstrumentPage.signInButton.shouldBe(visible, Duration.ofSeconds(1));
//        // presumed a logic to check if there's a signed-in user and if it does perform the sign-out
//    }

    @When("user navigates to the equity {string}")
    public void userNavigatesToTheEquity(String page) {
        open(page);
    }

    @Then("equity title is displayed")
    public void equityTitleIsDisplayed() {
        equityInstrumentPage.title.shouldBe(visible, Duration.ofSeconds(5));
    }

    @And("equity price is displayed")
    public void equityPriceIsDisplayed() {
        equityInstrumentPage.lastPrice.shouldBe(visible, Duration.ofSeconds(5));
    }

    @And("equity graph is displayed")
    public void equityGraphIsDisplayed() {
        equityInstrumentPage.graph.shouldBe(visible, Duration.ofSeconds(5));
    }

    @And("equity news and analysis section is displayed")
    public void equityNewsAndAnalysisSectionIsDisplayed() {
        equityInstrumentPage.newsSection.shouldBe(visible, Duration.ofSeconds(5));
        equityInstrumentPage.analysisSection.shouldBe(visible, Duration.ofSeconds(5));
    }

    @And("equity three tables are displayed and have content")
    public void equityThreeTablesAreDisplayedAndHaveContent() {
        equityInstrumentPage.technicalSummeryTbl.shouldBe(visible, Duration.ofSeconds(5));
        equityInstrumentPage.trendingStocksTbl.shouldBe(visible, Duration.ofSeconds(5));
        equityInstrumentPage.companyProfileBlock.shouldBe(visible, Duration.ofSeconds(5));
        equityInstrumentPage.companyProfileTable.shouldBe(visible, Duration.ofSeconds(5));
        equityInstrumentPage.companyProfileDescription.shouldNotBe(visible, Duration.ofSeconds(5));
    }
}

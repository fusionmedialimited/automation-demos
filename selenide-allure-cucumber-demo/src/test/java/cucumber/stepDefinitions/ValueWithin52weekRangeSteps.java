package cucumber.stepDefinitions;

import com.codeborne.selenide.testng.annotations.Report;
import cucumber.infrastructure.TestContext;
import cucumber.pages.EquityInstrumentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static cucumber.infrastructure.AllureAttachments.*;
import static org.testng.Assert.assertTrue;

//@Report
public class ValueWithin52weekRangeSteps {

    private TestContext testContext;
    private EquityInstrumentPage equityInstrumentPage = new EquityInstrumentPage();

    public ValueWithin52weekRangeSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("User navigates to the GameStop Corp {string} page")
    public void userNavigatesToHeGameStopCorp(String page) {
        open(page);
    }

    @When("^Get the current Stock value from the page$")
    public void getCurrentStockValue() {
        attachScreenshot("the page with current price value");

        String currentValue = equityInstrumentPage.lastPrice
                .shouldBe(visible)
                .getText();

        attachAsText("The current price value is: ", currentValue);

        testContext.scenarioData.put("currentValue", currentValue);
    }

    @And("^Get the 52 week range values$")
    public void get52weekRange() {
        String weekRange = $("[data-test='weekRange']")
                .scrollTo()
                .shouldBe(visible)
                .getText();

        attachScreenshot("52 week range values");

        String[] weekRangeValues = weekRange.split("-");

        attachAsText("The Start Week value value is: ", weekRangeValues[0]);
        attachAsText("The End Week value value is: ", weekRangeValues[1]);

        testContext.scenarioData.put("startWeekValue", weekRangeValues[0]);
        testContext.scenarioData.put("endWeekValue", weekRangeValues[1]);
    }

    @Then("^Verify that the current value is within the 52 week range values$")
    public void verifyValueWithinRange() {

        double startWeekValue = Double.parseDouble((String) testContext.scenarioData.get("startWeekValue"));
        double endWeekValue = Double.parseDouble((String) testContext.scenarioData.get("endWeekValue"));
        double currentValue = Double.parseDouble((String)testContext.scenarioData.get("currentValue"));

        assertTrue(startWeekValue < currentValue && currentValue < endWeekValue,
                "The lastPrice value: " + currentValue + " is not within the expected range: "
                        + startWeekValue + " - " + endWeekValue);
    }

}

package PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EquitiesInstrumentPage {

    public SelenideElement signInBtn = $(".desktop-only [data-test='login-btn'] span");
    public SelenideElement title = $("[class*='instrument-name'] > h1");
    public SelenideElement currentPrice = $("span[class^=instrument-price_last]");
    public SelenideElement graph = $(".overview-chart-body");
    public SelenideElement newsSection = $("[data-test='news-container']");
    public SelenideElement analysisSection = $("[data-test='analysis-container']");
    public SelenideElement technicalSummeryTbl = $("[data-test='instrument-tech-summary-table']");
    public SelenideElement trendingStocksTbl = $("[data-test='trending-stocks-table']");
    public SelenideElement companyProfileBlock = $x("//span[contains(text(),'Company Profile')]" +
            "//ancestor::div[1]");
    public SelenideElement companyProfileTable = $x("//span[contains(text(),'Company Profile')]" +
            "//following::div[1]");
    public SelenideElement companyProfileDescription = $x("//span[contains(text(),'Company Profile')]" +
            "//following::p");
    public SelenideElement fiftyTwoWeekRange = $("[data-test='weekRange']");


    public void goToEquitiesPage() {
        Selenide.open("/equities");
    }

    public void goToSpecificEquitiesPage(String URI) {
        Selenide.open("/equities" + URI);
    }

}

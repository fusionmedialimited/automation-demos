package cucumber.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EquityInstrumentPage {

    //page elements
    public SelenideElement signInButton = $("[data-test='login-btn']");
    public SelenideElement title = $("[class*='instrument-name'] > h1");
    public SelenideElement lastPrice = $("[data-test='instrument-price-last']");
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



    // page methods
    public void navigateToEquitiesPage() {
        Selenide.open("/equities");
    }

}

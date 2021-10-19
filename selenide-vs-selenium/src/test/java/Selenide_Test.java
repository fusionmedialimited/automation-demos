import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.junit5.TextReportExtension;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({TextReportExtension.class})
public class Selenide_Test {

    @Test
    public void Selenide_test() {
        Configuration.pageLoadTimeout = 60000;
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        open("https://www.investing.com/");

        try {
            if ($("#PromoteSignUpPopUp").isDisplayed()) {
                $(".largeBannerCloser").click();
                $("#PromoteSignUpPopUp").shouldNotBe(visible);
            }
        } catch (ElementNotFound var2) {
            System.out.println("The promotion pop-up wasn't shown, go on as usual");
        }

        $("#userAccount")
                .shouldBe(visible, Duration.ofSeconds(5))
                .$(".login").shouldHave(text("Sign In"))
                .click();

        $("#loginPopup")
                .should(Condition.appear, Duration.ofSeconds(5))
                .$("#loginFormUser_email")
                .shouldHave(attribute("placeholder", "Email"))
            .val("qa_automation@investing.com");

        $("#loginForm_password")
                .shouldHave(attribute("placeholder", "Password"))
                .val("Aa111111");

        $("[onclick='loginFunctions.submitLogin();']")
                .shouldBe(visible, Condition.enabled)
                .shouldHave(text("Sign In"), cssValue("background-color", "rgba(255, 165, 0, 1)"))
                .click();

        $(".myAccount.topBarText")
                .shouldBe(visible, Duration.ofSeconds(5L))
                .shouldHave(text("QA"));
    }
}


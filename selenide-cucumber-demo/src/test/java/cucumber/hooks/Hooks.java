package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private SelenideConfiguration selenideConfiguration;

    public Hooks(SelenideConfiguration selenideConfiguration) {
        this.selenideConfiguration = selenideConfiguration;
    }

    @Before
    public void BeforeScenario() {
        selenideConfiguration.getBrowser();
    }

    @After
    public void AfterScenario() {
        selenideConfiguration.closeSession();
    }
}

package cucumberTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/features/login.feature"},
        tags = {"@login_flow"},
        glue = {"stepDefinitions"}
)
public class LoginTest {
}


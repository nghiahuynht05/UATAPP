package UATPAXPEGASUS;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/UATPAXPEGASUS/login-passenger-app.feature")
public class RunCucumberTest {
}

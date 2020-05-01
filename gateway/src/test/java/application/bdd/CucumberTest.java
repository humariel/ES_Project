package application.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"}, dryRun = false,
        features = "src/test/resources/features")
public class CucumberTest {

}
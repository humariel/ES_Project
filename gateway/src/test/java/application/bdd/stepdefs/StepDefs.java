package application.bdd.stepdefs;

import java.util.UUID;
import application.Value;
import application.Simulator;
import application.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import application.bdd.CucumberSpringContextConfiguration;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;

public class StepDefs extends CucumberSpringContextConfiguration {
    double temp;
    double hum;
    double pre;
    boolean valid_hum = false;
    boolean valid_pre = false;
    boolean valid_temp = false;

    // dumb temperature test
    @Given("simulated temperature value")
    public void simulated_temperature_value() throws Throwable {
        temp = new Simulator(UUID.randomUUID().toString(), new Location("Point", 0.633084, -8.660537)).simulate().getTemperature();

    }

    @When("simulated temperature is within acceptable bounds")
    public void simulated_temperature_is_within_acceptable_bounds() throws Throwable {
        valid_temp = temp > -100 && temp < 100;

    }

    @Then("the simulated temperature is correct")
    public void the_simulated_temperature_is_correct() throws Throwable {
        assertEquals(valid_temp, true);

    }

    // dumb humidity test
    @Given("simulated humidity value")
    public void simulated_humidity_value() throws Throwable {
        hum = new Simulator(UUID.randomUUID().toString(), new Location("Point", 0.633084, -8.660537)).simulate().getHumidity();

    }

    @When("simulated humidity is within acceptable bounds")
    public void simulated_humidity_is_within_acceptable_bounds() throws Throwable {
        valid_hum = hum > 0 && hum < 100;

    }

    @Then("the simulated humidity is correct")
    public void the_simulated_humidity_is_correct() throws Throwable {
        assertEquals(valid_hum, true);

    }

    // dumb pressure test
    @Given("simulated pressure value")
    public void simulated_pressure_value() throws Throwable {
        hum = new Simulator(UUID.randomUUID().toString(), new Location("Point", 0.633084, -8.660537)).simulate().getPressure();

    }

    @When("simulated pressure is within acceptable bounds")
    public void simulated_pressure_is_within_acceptable_bounds() throws Throwable {
        valid_pre = pre > -100 && pre < 100;

    }

    @Then("the simulated pressure is correct")
    public void the_simulated_pressure_is_correct() throws Throwable {
        assertEquals(valid_pre, true);

    }
}
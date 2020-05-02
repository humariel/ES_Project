Feature: Test Humidity

  Scenario: SIMULATED HUMIDITY VALUES ARE ACCEPTABLE

    Given simulated humidity value 
    When simulated humidity is within acceptable bounds
    Then the simulated humidity is correct
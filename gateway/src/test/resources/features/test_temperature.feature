Feature: Test Temperature

  Scenario: SIMULATED TEMPERATURE VALUES ARE ACCEPTABLE

    Given simulated temperature value 
    When simulated temperature is within acceptable bounds
    Then the simulated temperature is correct
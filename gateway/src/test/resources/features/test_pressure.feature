Feature: Test Pressure

  Scenario: SIMULATED PRESSURE VALUES ARE ACCEPTABLE

    Given simulated pressure value 
    When simulated pressure is within acceptable bounds
    Then the simulated pressure is correct
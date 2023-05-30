@working-hours-processor
Feature: Working Hours Processor
  Check whether restaurant is during working hours at a given time.

  Scenario: Restaurant during working hours
    When the restaurant is during working hours
    Then working hours processor does not throw an exception

  Scenario: Restaurant not during working hours
    When restaurant is not during working hours
    Then working hours processor throws an exception
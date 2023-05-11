@table-repository
Feature: Table Repository
  Custom queries to retrieve tables from restaurant.

  Scenario: No tables found for restaurant
    Given there are no tables in restaurant
    When I search for a tables by restaurant
    Then the method should return an empty list of tables

  Scenario: Restaurant has three tables
    Given there are 3 tables in restaurant
    When I search for a tables by restaurant
    Then the method should return list of three tables

  Scenario: Find table by capacity and restaurant when existent
    Given there are no tables with given capacity for restaurant
    When I search for a tables by capacity 3 and restaurant
    Then the method should return list of three tables for capacity

  Scenario: Find table by capacity and restaurant when non-existent
    Given there are 3 tables with given capacity for restaurant
    When I search for a tables by capacity 3 and restaurant
    Then the method should return an empty list of tables for capacity
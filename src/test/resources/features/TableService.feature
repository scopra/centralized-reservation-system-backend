@table-service
Feature: Table Service
  Update Table entities by directly interacting with a repository.

  Scenario: Find all tables when database not empty
    Given more than one table exists in the database
    When I search for all tables
    Then the list of all tables is returned
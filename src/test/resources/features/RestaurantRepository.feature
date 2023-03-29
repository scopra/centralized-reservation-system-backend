Feature: Restaurant Repository
  Perform CRUD operations with Restaurant Entities

  Scenario: Find an existing restaurant by name
    Given there is a restaurant with name "McDonalds" in the database
    When I search for a restaurant by name using "McDonalds"
    Then the method should return the restaurant entity for the given name

  Scenario: Restaurant not found by name
    Given there is no restaurant with name "McDonalds" in the database
    When I search for a restaurant by name using "McDonalds"
    Then the method should return null

  Scenario: Find a restaurant ID by name
    Given there is a restaurant with name "McDonalds" in the database
    When I search for a restaurant ID by name using "McDonalds"
    Then the method should return the ID of the restaurant for the given name

  Scenario: Restaurant ID not found by name
    Given there is no restaurant with name "McDonalds" in the database
    When I search for a restaurant ID by name using "McDonalds"
    Then the method should return null ID
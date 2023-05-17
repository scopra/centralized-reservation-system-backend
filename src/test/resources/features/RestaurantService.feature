@restaurant-service
Feature: Restaurant Service
  Update Restaurant entities by directly interacting with a repository.

  Scenario: Find restaurant ID by name when existing in database
    Given the restaurant already exists in the database
    When I search for restaurant ID by name "KFC"
    Then the restaurant with correct ID is found

  Scenario: Find restaurant by ID when not existing in database
    When the restaurant does not exist in the database
    Then an exception is thrown when I search for ID by name "MCDonalds"

  Scenario: Find restaurant by name
    Given a restaurant with name "KFC" exists in the database
    When I search for restaurant by name "KFC"
    Then the restaurant with name "KFC" is found

  Scenario: Find all restaurants when database not empty
    Given more than one restaurant exists in the database
    When I search for all restaurants
    Then the list of all restaurants is returned

  Scenario: Find all restaurants when database empty
    Given the database is empty
    When I search for all restaurants
    Then the returned list of restaurants is empty

  Scenario: Find restaurant by ID
    Given the restaurant already exists in the database
    When I search for restaurant by ID
    Then the restaurant entity is returned

  Scenario: Find restaurants in municipality
    Given more than one restaurant in municipality "Novo Sarajevo" exists in the database
    When I search for restaurants in municipality "Novo Sarajevo"
    Then the list of all restaurants is returned

  Scenario: Find restaurants in municipality when not existing in database
    Given more than one restaurant in municipality "Novo Sarajevo" exists in the database
    When I search for restaurants in municipality "Staro Sarajevo"
    Then the returned list of restaurants is empty

  Scenario: Find restaurants in city
    Given more than one restaurant in city "Zenica" exists in the database
    When I search for restaurants in city "Zenica"
    Then the list of all restaurants is returned

  Scenario: Find restaurants in city when not existing in database
    Given more than one restaurant in city "Zenica" exists in the database
    When I search for restaurants in city "Sarajevo"
    Then the returned list of restaurants is empty
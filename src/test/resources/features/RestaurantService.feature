@restaurant-service
Feature: Restaurant Service
  Update Restaurant entities by directly interacting with a repository.

  Scenario: Update existing restaurant
    Given the restaurant already exists in the database
    When I update the restaurant
    Then the restaurant information is updated successfully

  Scenario: Add new restaurant
    Given the restaurant does not exist in the database
    When I update the restaurant
    Then the restaurant information is updated successfully

  Scenario: Find restaurant ID by name when existing in database
    Given the restaurant already exists in the database
    When I search for restaurant ID by name "Real"
    Then the restaurant with correct ID is found

  Scenario: Find restaurant by ID when not existing in database
    Given the restaurant already exists in the database
    When I search for restaurant ID by name "Fake"
    Then the findRestaurantIdByName method should return null

#   # TO BE REFACTORED
#  Scenario: Find restaurant by name
#    Given a restaurant with name "My Restaurant" exists
#    When I search for restaurant with name "My Restaurant"
#    Then the restaurant with name "My Restaurant" is found

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

#  Scenario: Find restaurants in municipality
#    Given a restaurant in municipality "Novo Sarajevo" exists
#    When I search for restaurants in municipality "Novo Sarajevo"
#    Then the list of all restaurants is returned
#
#  Scenario: Find restaurants in municipality
#    Given a restaurant in municipality "Novo Sarajevo" exists
#    When I search for restaurants in municipality "Staro Sarajevo"
#    Then the returned list of restaurants is empty
#
#  Scenario: Find restaurants in city
#    Given a restaurant in city "City1" exists
#    When I search for restaurants in city "City1"
#    Then the restaurants in city "City1" are found
#
#  Scenario: Find restaurant by address
#    Given a restaurant with address "1234 Main Street" exists
#    When I search for restaurant by address "1234 Main Street"
#    Then the restaurant with address "1234 Main Street" is found
#
#  Scenario: Check if restaurant exists
#    Given a restaurant with ID "4321" exists
#    When I check if restaurant with ID "4321" exists
#    Then the restaurant with ID "4321" exists
#
#  Scenario: Delete restaurant by ID
#    Given a restaurant with ID "8765" exists
#    When I delete restaurant with ID "8765"
#    Then the restaurant with ID "8765" is deleted
#
#  Scenario: Delete all restaurants
#    Given multiple restaurants exist
#    When I delete all restaurants
#    Then all restaurants are deleted
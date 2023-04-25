@restaurant-repository
Feature: Restaurant Repository
  Perform CRUD operations with Restaurant Entities

  Scenario Outline: Find a restaurant by name
    Given there is a restaurant with name "<restaurantName>" in the database
    When I search for a restaurant by name using "<restaurantName>"
    Then the method should return the restaurant entity for "<restaurantName>"

    Examples:
      | restaurantName |
      | McDonalds      |
      | KFC            |
      | BurgerKing     |

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

  Scenario: Find a restaurant by address
    Given there is a restaurant with the address "Marsala Tita 36" in the database
    When I search for a restaurant by address "Marsala Tita 36"
    Then the restaurant with the correct address is returned

  Scenario: Search for a restaurant by incorrect address
    Given there is a restaurant with the address "Marsala Tita 36" in the database
    When I search for a restaurant by address "Hrasnicka cesta 15"
    Then the method should return null

  Scenario: Search for restaurants by  municipality
    Given there is more than one restaurant with the municipality "Centar" in the database
    When I search for restaurants by municipality "Centar"
    Then the method should return a list of 2 restaurants

  Scenario: Search for restaurants by incorrect municipality
    Given there is more than one restaurant with the municipality "Centar" in the database
    When I search for restaurants by municipality "centar"
    Then the method should return an empty list

  Scenario: Search for restaurants by city
    Given there is more than one restaurant with the city "Sarajevo" in the database
    When I search for restaurants by city "Sarajevo"
    Then the method should return a list of 2 restaurants

  Scenario: Search for restaurants by city not in database
    Given there is more than one restaurant with the city "Sarajevo" in the database
    When I search for restaurants by city "Zenica"
    Then the method should return an empty list
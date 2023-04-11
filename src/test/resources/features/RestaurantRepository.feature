Feature: Restaurant Repository
  Perform CRUD operations with Restaurant Entities

  #RADI:
  Scenario: Find an existing restaurant by name
    Given there is a restaurant with name "McDonalds" in the database
    When I search for a restaurant by name using "McDonalds"
    Then the method should return the restaurant entity for the given name

    #RADI:
  Scenario: Restaurant not found by name
    Given there is no restaurant with name "McDonalds" in the database
    When I search for a restaurant by name using "McDonalds"
    Then the method should return null

    #NE RADI ???
  Scenario: Find a restaurant ID by name
    Given there is a restaurant with name "McDonalds" in the database
    When I search for a restaurant ID by name using "McDonalds"
    Then the method should return the ID of the restaurant for the given name

    #RADI
  Scenario: Restaurant ID not found by name
    Given there is no restaurant with name "McDonalds" in the database
    When I search for a restaurant ID by name using "McDonalds"
    Then the method should return null ID

    #RADI:
  Scenario: Find a restaurant by address
    Given there is a restaurant with the address "Marsala Tita 36" in the database
    When I search for a restaurant by address "Marsala Tita 36"
    Then the restaurant with the correct address is returned

    #RADI:
  Scenario: Search for a restaurant by incorrect address
    Given there is a restaurant with the address "Marsala Tita 36" in the database
    When I search for a restaurant by address "Hrasnicka cesta 15"
    Then the method should return null

    #RADI:
  Scenario: Search for restaurants by  municipality
    Given there is more than one restaurant with the municipality "Centar" in the database
    When I search for restaurants by municipality "Centar"
    Then the method should return a list of 2 restaurants with correct municipality

    #RADI:
  Scenario: Search for restaurants by incorrect municipality
    Given there is more than one restaurant with the municipality "Centar" in the database
    When I search for restaurants by municipality "centar"
    Then the method should return an empty list

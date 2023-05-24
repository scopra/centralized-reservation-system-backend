Feature: Making Reservation in a Restaurant
  As a customer
  I want to make a reservation in a restaurant
  So that I can enjoy a meal with my friends or family

  Scenario: Reservation made outside working hours
    Given the restaurant is open from 9:00 AM to 10:00 PM
    And the current time is 11:00 PM
    When I try to make a reservation
    Then I should see an error message indicating that the reservation cannot be made outside working hours




#@rule-repository
#Feature: Rule Repository
#  Find a list of rules by restaurant.
#
#  Scenario: Three rules defined for restaurant
#    Given the restaurant has three rules defined
#    When I search for rules by restaurant
#    Then the method returns a list of three rules
#
#  Scenario: No rules defined for restaurant
#    Given the restaurant has no rules defined
#    When I search for rules by restaurant
#    Then the method returns an empty list of rules
Feature: Update business address
  As a customer who has relocated to a new office or work location,
  I want to update my business address,
  so that I receive physical correspondence and maintain accurate records for billing and compliance purposes.

  Scenario: Current business address is displayed with all components
    Given the customer has business address:
      | street      | city        | stateProvince | postalCode | country        |
      | 100 Main St | Springfield | Illinois      | 62701      | United States  |
    When the customer requests their current business address
    Then the customer sees their business address:
      | street      | city        | stateProvince | postalCode | country        |
      | 100 Main St | Springfield | Illinois      | 62701      | United States  |


  Scenario: No business address returns not found
    Given the customer has no business address
    When the customer requests their current business address
    Then the response status is 404

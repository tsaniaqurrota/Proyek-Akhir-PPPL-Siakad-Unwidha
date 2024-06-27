Feature: Logout functionality

  Scenario: User can logout successfully
    Given User login
    When User clicks logout
    Then User should be redirected to the login page

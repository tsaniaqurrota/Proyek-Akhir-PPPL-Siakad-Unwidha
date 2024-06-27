Feature: User Login

  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User submit valid credentials
    Then User should be redirected to the Data Dosen Page

  Scenario Outline: User login with invalid credentials
    Given User is on the login page
    When user submits "<email>" and "<password>"
    Then user still on the login page

    Examples:
      | email                   | password        |
      | siakadadmin@mail.com    | password1       |
      | siakadadmin1@mail.com   | password        |
      | siakadadmin1@mail.com   | password1       |
      |                         | password        |
      | siakadadmin@mail.com    |                 |
      |                         |                 |

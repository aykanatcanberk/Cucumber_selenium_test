Feature: Login

  Scenario: User can login with valid credentials
    Given the user is on the login page
    When the user enters "Admin" into the username field
    And the user enters "admin123" into the password field
    And the user clicks the login button
    Then the user should be redirected to the home page

  Scenario: User cannot login with invalid credentials
    Given the user is on the login page
    When the user enters "invalidUsername" into the username field
    And the user enters "invalidPassword" into the password field
    And the user clicks the login button
    Then an error message should be displayed
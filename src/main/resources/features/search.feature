Feature: Search Operations

  Background:
    Given the user has logged in to the application

  Scenario: Share post and check it
    When the user selects the Buzz menu item
    Then the user should be directed to the Buzz page
    And the user posts something "I will see you again."

  Scenario: Search for menu item at SearchBox and verify search results
    When the user enters "Time" in the search bar
    And the user performs the search
    Then the search results should contain "Time"

  Scenario: Add a new employee and verify redirection
    When the user clicks on the PIM menu item
    And the user clicks on the Add button
    And the user fills in the First Name and Last Name fields and clicks Save
    Then the user should be redirected to another page

  Scenario: User logs out of the application
    When The user clicks on the profile picture
    And The user selects Logout from the dropdown menu
    Then The user should be redirected to the login page

  Scenario: Search for users with the "Admin" role
    When the user clicks on the Admin menu item
    Then Click Dropdown Arrow
    Then Chose dropdown option "Admin"
    And  The user clicks Search button


  Scenario: Delete all employee records
    When Click on the PIM menu item
    Then Click on the checkbox item to choose all of records
    Then Click on Delete Selected button
    Then Confirm to deletion of all records

  Scenario: Change Profile Picture
    When User clicks on the My Info menu
    And User navigates to the profile picture page
    Then Upload the profile picture and save it

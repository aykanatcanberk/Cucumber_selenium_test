# OrangeHRM Test Automation Project with

## Overview

This project contains automated test cases for the OrangeHRM demo site using Cucumber and Selenium in Java. The test cases cover various functionalities, including login, search operations, adding and deleting employees, and profile picture updates.

## Features

### Login

- **User can login with valid credentials**
  - Test if the user can successfully log in with valid username and password.
- **User cannot login with invalid credentials**
  - Test if an error message is displayed when incorrect credentials are used.

### Search Operations

- **Share post and check it**
  - Test sharing a post on the Buzz page.
- **Search for menu item at SearchBox and verify search results**
  - Test searching for a term in the search bar and verify if the results contain the expected term.
- **Add a new employee and verify redirection**
  - Test adding a new employee and verify if the user is redirected to the appropriate page.
- **User logs out of the application**
  - Test logging out and verify if the user is redirected to the login page.
- **Search for users with the "Admin" role**
  - Test searching for users with the "Admin" role and verify the results.
- **Delete all employee records**
  - Test selecting and deleting all employee records.
- **Change Profile Picture**
  - Test uploading and saving a new profile picture.

## Setup

### Prerequisites

- **Java Development Kit (JDK) 8 or higher**
- **Maven**
- **Selenium WebDriver**
  - Version: `4.23.0`
- **Cucumber**
  - `cucumber-java8`: Version `7.0.0`
  - `cucumber-core`: Version `7.0.0`
  - `cucumber-junit`: Version `7.0.0`
  - `cucumber-java`: Version `5.5.0`
- **JUnit**
  - Version: `RELEASE`
- **ChromeDriver**
  - Note: Specific version for ChromeDriver should match the version of Chrome browser installed.


### Installation

1. **Clone the Repository**
   
`git clone https://github.com/aykanatcanberk/orangehrm-test-automation.git`
  

## Important Notice

**Warning:** The OrangeHRM demo site allows multiple users to perform actions simultaneously. This can lead to conflicts or inconsistencies in test results, as different tests might interact with the same data or perform operations concurrently. Please be aware of this potential issue when analyzing test results and consider running tests in isolated environments if possible.

## Test Results
![Ekran görüntüsü 2024-08-09 174213](https://github.com/user-attachments/assets/4b87c600-9fda-471c-b02d-53ec8cd21308)

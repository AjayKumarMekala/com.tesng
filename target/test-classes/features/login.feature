Feature: Login functionality
  @smoke
  Scenario: Login should be success
    Given Launch the application
    And Enter "Admin" and "admin123"
    When User click on login button
    Then Login should be successfull
@sanity
  Scenario: Login should not be success
    Given Launch the application
    And Enter "Admin" and "1234"
    When User click on login button
    But Login should not be successfull

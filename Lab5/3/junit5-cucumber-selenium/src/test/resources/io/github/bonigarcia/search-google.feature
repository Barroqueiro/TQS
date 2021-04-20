Feature: Search in Google
 
  Scenario: Seek for Selenium-Jupiter documentation
    When I navigate to "https://blazedemo.com/"
    And I want to go from "Boston" to "Berlin"
    And I enter my information
    Then I should be shown results with the price "555"

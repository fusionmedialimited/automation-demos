@Automation
@Equities
Feature: Verify that the current GameStop Corp stock value is within the 52 week range
  I want to verify that the current stock value for the GameStop Corp on the page
  is within the values of the the 52 Week Range from the page

  Scenario Outline: Compare the current stock value with the 52 week range values
    Given User navigates to the GameStop Corp "<page>" page
    When Get the current Stock value from the page
    And Get the 52 week range values
    Then Verify that the current value is within the 52 week range values

    Examples:
      | page                   |
      | equities/gamestop-corp |
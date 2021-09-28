@Automation
@Equities
@REFA-2711
Feature: Equities: Element Existence
  element existence across a sample of 5 equities

  @REFA-2711
  Scenario Outline: Check Equity Page Elements
    #Given a signed-out user
    When user navigates to the equity "<page>"
    Then equity title is displayed
    And equity price is displayed
    And equity graph is displayed
    And equity news and analysis section is displayed
    And equity three tables are displayed and have content

    Examples:
      | page                              |
      | equities/apple-computer-inc       |
      | equities/google-inc               |
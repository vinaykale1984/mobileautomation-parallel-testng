@Launch
Feature: Login scenario

  Scenario Outline: Login with invalid username
    When I enter username "<Username>"
    And I enter the password "<Password>"
    And I login
    And Login should with an error "<Error>"


    Examples:
      | Username       | Password     | Error                |
      | invalidUsename | secret_saice | Username is required |


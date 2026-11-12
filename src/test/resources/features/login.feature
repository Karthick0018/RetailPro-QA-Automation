@ui @login
Feature: Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the products page is displayed

  Scenario Outline: Login fails with invalid credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the error message contains "<message>"

    Examples:
      | username        | password     | message                              |
      | locked_out_user | secret_sauce | locked out                           |
      | standard_user   | wrong_pass   | Username and password do not match   |
      |                 | secret_sauce | Username is required                 |
      | standard_user   |              | Password is required                 |

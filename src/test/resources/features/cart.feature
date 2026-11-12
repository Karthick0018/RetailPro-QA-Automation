@ui @cart
Feature: Shopping cart

  Background:
    Given the user is logged in

  Scenario: Add products to the cart
    When the user adds 2 products to the cart
    Then the cart badge shows 2
    When the user opens the cart
    Then the cart contains 2 items

  Scenario: Remove a product from the cart
    When the user adds 2 products to the cart
    And the user opens the cart
    And the user removes the first item from the cart
    Then the cart contains 1 items

  Scenario: Sort products by price low to high
    When the user sorts products by "Price (low to high)"
    Then the products are sorted by price ascending

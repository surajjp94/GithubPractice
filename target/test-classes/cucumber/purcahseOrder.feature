@tag
Feature: Purchase the Order from ecommerce website

Background:

Given I landed o ecommerce page

 @tag1
  Scenario: Positive test of purchasing the order
    Given login with UserName <name> and Password <Password>
    When I add Product <ProductName> to cart
    When Checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message displayed on Confirmation page

    Examples: 
      | name  								| Password 			|   ProductName |
      | surajsuri07@gmail.com |     Suraj@123 |  QWERTY				|
     

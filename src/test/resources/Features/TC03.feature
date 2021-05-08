Feature: feature to test hepsiburada.com cart functionality

  Scenario: cart functionality on Hepsiburada

    Given user is on Hepsiburada
    When search the product and click Enter key
    And select the product that want to buy
    And click to button to add into cart
    And User click the button to go to cart
    And User check the product
    And User search the product again
    And Select same product by another merchant
    And click to button to add into cart again
    And go to the cart again
    Then User Check the Cart
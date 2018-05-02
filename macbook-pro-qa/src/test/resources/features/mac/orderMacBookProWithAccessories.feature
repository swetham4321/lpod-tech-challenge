@qa
Feature: Buying a MacBook Pro on the website
  As an online customer
  I want to choose a MacBook with accessories on the website
  So that I can buy it online

  Scenario: A MacBook 15” with accessories can be ordered on the website

    Given I go to the website
    When I choose a MacBook Pro with the following features and accessories
      | Option          | Specification        |
      | Screen          | 15inch               |
      | Processor       | 2.9GHz               |
      | Colour          | Silver               |
      | Software        | Logic Pro X          |
      | DisplayAdapter  | USB-C to USB Adapter |
#    Then I can place an order for it.
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: 01. Sunday isn't Friday
    Given Open application home screen
    And I want to open menu setting passenger
    Given I want to open "Profile" setting
    Given I want to open "Personal info" in Profile Setting
    And I want to touch button Edit
    Given I want to edit info account with data
      | firstName | lastName | email        | national | gender | birthday   |
      | Customer  | Edit     | abc@edit.com | 2020     | Male   | 01/10/1992 |

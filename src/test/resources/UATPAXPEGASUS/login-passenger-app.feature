Feature: Login driver pegasus
  As an passenger,
  I want to login passenger pegasus app.

  Background:

  Scenario: 01. Check the content of popup confirm phone number
    Given I want to input phone number form login with data
      | phoneNumber |
      | 348811525   |
    And I want to tap checkbox term of use
    And I want to tap button Accept form login
    And I want to get content message in popup
    Then I should get the response message matches with
      | response                                              |
      | {"Is this your correct phone number? (+84)348811525"} |

  Scenario: 02. Check login passenger app with phone number is no format phone number
    Given I want to input phone number form login with data:
      | phoneNumber |
      | 204555000   |
    And I want to tap checkbox term of use
    And I want to tap button Accept form login
    When I want to tap button "Yes" form login
    And I want to get message error
    Then I should get the response message matches with
      | response                                                                           |
      | {"The number you entered doesn't appear to be valid. Please check and try again."} |

  Scenario: 03. Check login passenger app with phone number is characters
    Given I want to input phone number form login with data
      | phoneNumber |
      | abcd        |
    And I want to tap checkbox term of use
    And I want to tap button Accept form login
    When I want to tap button "Yes" form login
    And I want to get message error
    Then I should get the response message matches with
      | response                                                                           |
      | {"The number you entered doesn't appear to be valid. Please check and try again."} |

  Scenario: 04. Check login passenger app with verify code
#    Given I want to reset data login driver with data
#      | platform | number    | country | fleetCode |
#      | android  | 348811502 | VN      | auto      |

    And I want to input phone number form login with data
      | phoneNumber |
      | 7401123123  |
    And I want to tap checkbox term of use
    And I want to tap button Accept form login
    When I want to tap button "Yes" form login
    And I should open form verify code and send data
      | verifyCode |
      | 1111       |
    Then I want to get message error
    Then I should get the response message matches with
      | response                         |
      | {"en":"Invalid validation code"} |

  Scenario: 05. Check login driver app successful with verify code default
#    Given I want to reset data login driver with data
#      | platform | number    | country | fleetCode |
#      | android  | 348811502 | VN      | auto      |

    And I want to input phone number form login with data
      | phoneNumber |
      | 348811502   |
    And I want to tap checkbox term of use
    And I want to tap button Accept form login
    When I want to tap button "Yes" form login
    And I should open form verify code and send data
      | verifyCode |
      | 3210       |
    Then Open application home screen

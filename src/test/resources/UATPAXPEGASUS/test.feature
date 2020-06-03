Feature: List stepdefs on project
  I want get all stepdefs on project

  Scenario: 01. Socket connect
    Given Register and start status receive booking type "Now" of driver
      | number    | country | ime             | verifyCode | fleetId | platform | appType | password |
      | 348811501 | VN      | 1E598DC1FF28421 | 3210       | auto    | android  | driver  | password |
    Given I should get the response data matches with
      | serviceNow | serviceLate | serviveHourly | serviceSuperHelper | firstName | lastName | phoneNumber | email | national | gender | birthDate |  |  |  |  |  |  |  |  |  |  |
      |            | 123         |               |                    |           |          |             |       |          |        |           |  |  |  |  |  |  |  |  |  |  |
    Given I should get the response message matches with
      | test |
      | 01   |
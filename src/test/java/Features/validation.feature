Feature: Creating Alert

  @testone
  Scenario: verify Alert
    Given Add create Alert PayLoad
    When  user calls  Create Alert API post Http Request
    Then Api call is success with statuscode 200
    And  "success" in responseBody  is "true"



  @testone
  Scenario: verify Alert
    Given Add launchthebrowser
    When  User Create the event
    Then  Verify the event name
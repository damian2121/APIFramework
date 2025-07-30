Feature: Validating Place API's

  Scenario: Verify if place is being successfully added using AddPlaceApi
    Given Add Place Payload
    When use calls "AddPlaceApi" with Post http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

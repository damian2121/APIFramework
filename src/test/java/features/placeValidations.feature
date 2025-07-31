Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully added using AddPlaceApi
    Given Add Place Payload with name <name>, language <language>, address <address>
    When use calls "AddPlaceApi" with Post http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      | name         | language | address          |
      | "Test Place" | "en-US"  | "123 Test St."   |
      | "Demo Place" | "fr-FR"  | "456 Demo Ave."  |

Feature: Validating Place API's
@AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceApi
    Given Add Place Payload with name <name>, language <language>, address <address>
    When user calls "AddPlaceAPI" with "Post" http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to <name> using "getPlaceAPI"

    Examples:
      | name         | language | address          |
      | "Test Place" | "en-US"  | "123 Test St."   |
      #| "Demo Place" | "fr-FR"  | "456 Demo Ave."  |

@DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the Api call is success with status code 200
    And "status" in response body is "OK"

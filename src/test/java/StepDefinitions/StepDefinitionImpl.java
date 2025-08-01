package StepDefinitions;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class StepDefinitionImpl extends Utils {
    RequestSpecification request;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with name {string}, language {string}, address {string}")
    public void add_place_payload_with_name_language_address(String name, String language, String address) throws IOException {
        request = given().log().all()
                .spec(requestSpecification()).body(data.AddPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void use_calls_with_post_http_request(String resource, String httpMethod) {

        resspec = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType("application/json")
                .build();
        APIResources resourceAPI = APIResources.valueOf(resource);
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = request.when()
                    .post(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = request.when()
                    .get(resourceAPI.getResource());
        }
    }
    @And("the Api call is success with status code {int}")
    public void the_api_call_is_succes_with_status_code(Integer int1) {
        response.then()
                .log().all()
                .spec(resspec).extract().response();
        Assert.assertEquals(response.getStatusCode(), int1, "Status code is not matching");
    }
    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);

    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToNameUsingGetPlaceAPI(String name, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        request = given().spec(requestSpecification()).queryParam("place_id", place_id);
        use_calls_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, name, "Place name does not match");
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        request = given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
    }
}

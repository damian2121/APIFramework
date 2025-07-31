package StepDefinitions;

import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.Location;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitionImpl extends Utils {
    RequestSpecification request;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with name {string}, language {string}, address {string}")
    public void add_place_payload_with_name_language_address(String name, String language, String address) throws IOException {
        request = given().log().all()
                .spec(requestSpecification()).body(data.AddPlacePayload(name, language, address));
    }

    @When("use calls {string} with Post http request")
    public void use_calls_with_post_http_request(String string) {
        resspec = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType("application/json")
                .build();

        response = request.when()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .spec(resspec).extract().response();
    }
    @Then("the Api call is success with status code {int}")
    public void the_api_call_is_succes_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), int1, "Status code is not matching");
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(js.getString(keyValue), expectedValue);

    }

}

package StepDefinitions;

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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinitionImpl {
    RequestSpecification request;
    ResponseSpecification resspec;
    Response response;

    @Given("Add Place Payload")
    public void add_place_payload() {
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setName("Frontline house");
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setAddress("29, side layout, cohen 09");
        ap.setWebsite("http://google.com");
        ap.setLanguage("French-IN");
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);

        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);

        RequestSpecification reqspec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();


        resspec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
                .expectContentType("application/json")
                .build();

        request = given()
                .log().all().spec(reqspec).body(ap);
    }
    @When("use calls {string} with Post http request")
    public void use_calls_with_post_http_request(String string) {
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

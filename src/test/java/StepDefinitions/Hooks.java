package StepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws Throwable {
        StepDefinitionImpl stepDefinition = new StepDefinitionImpl();
        if(StepDefinitionImpl.place_id == null) {
            stepDefinition.add_place_payload_with_name_language_address("Test", "English", "123 Test Street");
            stepDefinition.use_calls_with_post_http_request("AddPlaceAPI", "POST");
            stepDefinition.the_api_call_is_succes_with_status_code(200);
            stepDefinition.in_response_body_is("status", "OK");
            stepDefinition.in_response_body_is("scope", "APP");
            stepDefinition.verifyPlace_idCreatedMapsToNameUsingGetPlaceAPI("Test", "getPlaceAPI");
        }

    }
}

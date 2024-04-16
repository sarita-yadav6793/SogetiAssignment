package stefDefs;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.GetApiDetails;
import pojo.Places;

public class APIAutomation {
	private Logger logger= Logger.getLogger(this.getClass());
	RequestSpecification request;
	Response response;
	GetApiDetails getApiDetails;
	String placeName;
	@Given("User set GET api endpoint")
	public void user_set_get_api_endpoint() {
		RestAssured.baseURI="http://api.zippopotam.us/de/bw/stuttgart";
		request=RestAssured.given();
	}
	@When("user send GET Http request")
	public void send_het_http_request() {
		response = request.request(Method.GET, "");
	}

	@Then("response time is below one sec")
	public void response_time_is_below_one_sec() {
		long responseTime=response.getTimeIn(TimeUnit.SECONDS);
		if(responseTime<1) {
			logger.info("Response time is less than 1 sec, i.e.,"+responseTime);
		}
		else {
			logger.info("Response time is more than 1 sec, i.e.,"+responseTime);
		}
	}
	@Then("Country in response body is Germany")
	public void country_in_response_body_is_germany() {
		getApiDetails = response.as(GetApiDetails.class);
		String country=getApiDetails.getCountry();
		if(country.equals("Germany")) {
			logger.info("Country in response code is Germany");
		}
		else {
			logger.error("Country in response code is Germany");
		}
		
	}
	@Then("State in response body is  Baden-Württemberg")
	public void state_in_response_body_is_baden_württemberg() {
		String state= getApiDetails.getState();
		if(state.equals("Baden-Württemberg")) {
			logger.info("Country in response code is Baden-Württemberg");
		}
		else {
			logger.error("Country in response code is Baden-Württemberg");
		}
	}
	@Then("Post Code in response body is {string}")
	public String post_code_in_response_body_is(String expectedpostcode) {
	    List<Places> postcode= getApiDetails.getPlaces();
	    for(int i=0;i<postcode.size();i++) {
	    	String post= postcode.get(i).getPostCode();
	    	if(post.equals(expectedpostcode)) {
	    		logger.info("Post Code in response body is "+expectedpostcode);
	    		placeName= postcode.get(i).getPlaceName();
	    		break;
	    	}
	    	else {
	    		logger.info("Post Code in response body is not "+expectedpostcode);
	    	}
	    	
	    }
	    return placeName;
	    
	}
	@Then("Place name in response body is Stuttgart Degerloch")
	public void place_name_in_response_body_is_stuttgart_degerloch() {
		if(placeName.equals("Stuttgart Degerloch")) {
			logger.info("Place name is: "+placeName);
		}
	}

	@Given("when user set api for {string} and {string}")
	public void when_user_set_api_for_and(String string, String string2) {
		RestAssured.baseURI="http://api.zippopotam.us/"+string+"/"+string2;
		request=RestAssured.given();
	}
	@Then("user gets valid Http response code is {string}")
	public void user_gets_valid_http_response_code_is(String string) {
		int responseCode=response.getStatusCode();
		Assert.assertEquals("Status code is not 200",responseCode,200);
		logger.info("Status code is OK as"+responseCode);
	}
	@Then("Content type is application\\/json")
	public void content_type_is_application_json() {
		String contentType= response.getContentType();
		Assert.assertEquals("Content type is not JSON",contentType,"application/json");
		logger.info("content type is"+contentType);
	}
	@Then("user verify the {string}")
	public void user_verify_the(String string) {
		JsonPath js = response.jsonPath();
		String place_name= js.get("places[0].'place name'");
		Assert.assertEquals("Place name is not"+string,string, place_name);
		logger.info(place_name + " is fetched correctly as per postal code and country");
		//getApiDetails = response.as(GetApiDetails.class);
	}


}

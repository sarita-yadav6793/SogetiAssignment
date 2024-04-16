package stefDefs;

import Pages.Automation;
import Pages.Homepage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UIAutomation {
	Homepage hp = new Homepage();
	Automation aut = new Automation();

	@Given("User is on Sogeti Homepage")
	public void user_is_on_sogeti_homepage() {
		hp.verifySogetiLogo();
	}
	@When("User clicks Automation Link")
	public void user_clicks_automation_link() {
		hp.clickAutomation();
	}
	@Then("User should be able to see Automation Page")
	public void user_should_be_able_to_see_automation_page() {
		aut.verifyAutomationText();
	}
	@Then("Services and Automation Link should be selected")
	public void services_and_automation_link_should_be_selected() {
		aut.verifyServicesAutomationSelected();
	}
	@When("User submit contact us form with country {string}")
	public void user_submit_contact_us_form_with_country(String country) {
	    aut.fillcontactform(country);
	}

	
	/* this below step can not be verified because of captcha which is by definition a verification to verify it is human therefore
	 *verifying it with automated program solely finishes it purpose.
	 * 
	 * Ways to overcome:
	 * 1. either add manual wait and then do manually cerify captcha --- but this method is not useful for more number of tests and 
	 * when we are running in pipeline.
	 * 2. BY disabling captcha in testing environment using google reCAPTCHA v2 by using site key and secret key where 
	 * it shows that captcha is bypassed for testing purpose.
	 * 3. By adding hooks which tells that it is automated program not the human and its like bypassing captcha
	 */
	
	
	@Then("User should be able to verify thank you message {string}")
	public void user_should_be_able_to_verify_thank_you_message(String message) {
		aut.verifySuccessMsg(message);
	}
	
	@When("user clicks Worldwide Link")
	public void user_clicks_worldwide_link() {
		hp.clickWorldWide();
	}
	@When("select the country")
	public void select_the_country() {
		hp.verifyCountryLinks();
	}
	@Then("User should be navigated to specific country link")
	public void user_should_be_navigated_to_specific_country_link() {
		hp.displayCountryLinkWorking();
	}



}

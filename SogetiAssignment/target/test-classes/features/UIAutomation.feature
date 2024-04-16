@uiautomation
Feature: Verify Sogeti UI Application 
Background:	 User is able to access Sogeti application
		Given User is on Sogeti Homepage

@scenario1ui
Scenario: TestCase1 - verify Services and Automation link
		When User clicks Automation Link
		Then User should be able to see Automation Page 
		And Services and Automation Link should be selected
		

@scenario2ui
Scenario:TestCase2 - submit contact us form and verify successful message
		When User clicks Automation Link
		And User submit contact us form with country "Argentina"
		Then User should be able to verify thank you message as "Thank you for contacting us."

@scenario3ui		 
Scenario:TestCase3 - Verify all country links under Worldwide are working
		When user clicks Worldwide Link
		And select the country
		Then User should be navigated to specific country link



 
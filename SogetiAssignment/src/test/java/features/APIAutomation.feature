@apiautomation
Feature: Sogeti API tests assignment automation
  @scenario1api
  Scenario: User is able to access API and verify country, state and postcode from the response
    Given User set GET api endpoint
    When user send GET Http request
    Then user gets valid Http response code is '200'
    And Content type is application/json    
    And response time is below one sec
    And Country in response body is Germany
    And State in response body is  Baden-Württemberg
		And Post Code in response body is '70597'
		And Place name in response body is Stuttgart Degerloch

  @scenario2api
  Scenario Outline: Verify place name for postal code and for following countries
    Given when user set api for "<Country>" and "<Postal Code>"
    When user send GET Http request
    Then user gets valid Http response code is '200'
    And Content type is application/json
    And response time is below one sec
    And user verify the "<Place Name>"
    Examples: 
      | Country  | Postal Code | Place Name    |
      | us       |     90210   | Beverly Hills |
      | us       |     12345   | Schenectady   |
      | ca       |      B2R    |  Waverley     |

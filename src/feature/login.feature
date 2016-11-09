@login_flow
Feature: Testing login with invalid username and password

Scenario: Login with invalid username and password
	When I navigate to http://www.vodafone.co.nz/
	And I click on My Vodafone in the top right corner menu
	And I click Login button on the My Vodafone page
	And I enter wrong username:test and password:test
	And I click submit button
	Then I should see the warning message "Your email address is not registered for My Vodafone."
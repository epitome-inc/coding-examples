Scenario: Logging in with valid credentials
 Given I am at the Login page 'www.epitome.inc'
  When I fill the account email textbox with value 'myname@mymail.com'
   And I fill the password textbox with value 'mypassword'
   And I click the login button
  Then I should be at the home page
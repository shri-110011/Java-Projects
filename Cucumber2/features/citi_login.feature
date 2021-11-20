Feature: Citi login page scenarios

Scenario: Citi login
Given user is on login page
When user enters valid username and password
Then user is navigated to 'https://uat2.online.citi.com/US/ag/mrc/dashboard' 
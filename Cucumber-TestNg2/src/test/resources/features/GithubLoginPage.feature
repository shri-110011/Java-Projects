Feature: Github  

@demo
Scenario Outline: Github Login
Given I am on the Github login page whose url is '<GithubLoginPage_url>'
When the user enters the right '<username>' and '<password>'
#And the user clicks on the sign on button
#Then the user is navigated to the '<Github_dashboard_url>'

Examples:
|GithubLoginPage_url|username|password|Github_dashboard_url|
|https://github.com/login|abc123@gmail.com|abc123|https://github.com/|

Scenario: Github Dashboard
Given I am on the Github dashboard page whose url is 'https://github.com/'
Then I see that the account name is 'shri110011'
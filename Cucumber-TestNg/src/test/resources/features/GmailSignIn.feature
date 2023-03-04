Feature: Gmail Sign in

@GmailLogin
Scenario Outline: User should see the Gmail login page on Sign in
Given I am on the google search url "<url>"
When I click on Sign in button
Then I should see the Gmail Sign in page

Examples:
|url|
|https://www.google.com|

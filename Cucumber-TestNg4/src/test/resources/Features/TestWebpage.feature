Feature: Loading paragraph dynamically

Scenario Outline: To verify that the paragraph gets loaded dynamically
Given I enter the url 'http://localhost:3000'
And I land on the Test Webpage
#When I scroll down to find the Show Text button and click on it
#Then the paragraph should be displayed
When I select favourite color as "<favouriteColor>"
And I click on the show favourite color button
Then My favourite color should be displayed "<favouriteColor>"

Examples:
|favouriteColor|
|Blue|
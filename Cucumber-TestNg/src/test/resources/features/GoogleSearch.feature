Feature: Google Search

@GoogleSearch
Scenario Outline: User should get results on Google search
Given I am on the google search url "<url>"
When I search for the word "<word>" 
Then I should get some results

Examples:
|url|word|
|https://www.google.com|dog|
Feature: Google Search

Scenario Outline: To search for a given keyword in Google
Given I am on the Google Search Page '<url>'
When I enter the '<searchText>'
Then I verify I get search results greater than '<count>'

Examples:
|url|searchText|count|
##@data@src/test/java/dataProvider/TestData.xlsx@Sheet1
|https://www.google.com|Dragon Ball Super|80|
|https://www.google.com|Detective Conan|100|
|https://www.google.com|Java Tutorial|150|
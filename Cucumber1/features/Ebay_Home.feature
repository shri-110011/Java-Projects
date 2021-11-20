
Feature: Ebay Home Page scenarios

@P1 @P3
Scenario: Advanced Search Link
#@P1 is a tag and is used to group multiple Scenarios.

Given I am on Ebay Home Page
When I click on Advanced search link
Then I navigate to Advanced Search Page 

@P3 @setCookies @Test
Scenario: Search items count

Given I am on Ebay Home Page
When I search for 'iphone 11' 
#'<iphone 11>' is a variable/parameter
Then I get search results greater than 80

@P4 @setCookies
Scenario: Search items count2

Given I am on Ebay Home Page
When I search for "soap" under 'baby' category

@P6
Scenario Outline: Home page links
Given I am on Ebay Home Page
When I click on '<link>' 
#'<link>' is an outline
Then I verify that the page navigate to '<url>' and title contains '<title>'

 Examples:
 |link|url|title|
 |Motors|https://www.ebay.com/sch/6028/i.html?_from=R40&_nkw=Auto+Parts+Accessories&_blrs=recall_filtering|Auto Parts Accessories in Parts & Accessories \| eBay|
 |Electronics|https://www.ebay.com/b/Electronics/bn_7000259124|Electronics products for sale \| eBay|
 |Fashion|https://www.ebay.com/b/Fashion/bn_7000259856|Fashion products for sale \| eBay|
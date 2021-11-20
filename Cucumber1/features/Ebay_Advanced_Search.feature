
Feature: Ebay Advanced Search Page

@P2
Scenario: Ebay Logo on Advanced Search Page

Given I am on Ebay Advanced Search Page
When I click on Ebay Logo
Then I am navigated back to Ebay Home Page

@P5 @setCookies
Scenario: Advance search an item

Given I am on Ebay Advanced Search Page
When I advance search an item
 | keyword | excludedWord | minPrice | maxPrice |
 | iphone 11 | refurbished | 300 | 900 |
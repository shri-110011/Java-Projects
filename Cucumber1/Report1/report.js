$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:features/Ebay_Home.feature");
formatter.feature({
  "name": "Ebay Home Page scenarios",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Advanced Search Link",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I am on Ebay Home Page",
  "keyword": "Given "
});
formatter.match({
  "location": "steps.Ebay_Home_Steps.i_am_on_Ebay_Home_Page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on Advanced search link",
  "keyword": "When "
});
formatter.match({
  "location": "steps.Ebay_Home_Steps.i_click_on_Advanced_search_link()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I navigate to Advanced Search Page",
  "keyword": "Then "
});
formatter.match({
  "location": "steps.Ebay_Home_Steps.i_navigate_to_Advanced_Search_Page()"
});
formatter.result({
  "status": "passed"
});
});
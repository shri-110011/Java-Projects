$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:features/Ebay_Advanced_Search.feature");
formatter.feature({
  "name": "Ebay Advanced Search Page",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Advance search an item",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@P5"
    },
    {
      "name": "@setCookies"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am on Ebay Advanced Search Page",
  "keyword": "Given "
});
formatter.match({
  "location": "steps.Ebay_Advanced_Search.i_am_on_Ebay_Advanced_Search_Page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I advance search an item",
  "rows": [
    {},
    {}
  ],
  "keyword": "When "
});
formatter.match({
  "location": "steps.Ebay_Advanced_Search.i_advance_search_an_item(io.cucumber.datatable.DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});
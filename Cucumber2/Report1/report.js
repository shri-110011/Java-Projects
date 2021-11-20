$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:features/citi_home.feature");
formatter.feature({
  "name": "Citi home page scenarios",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Navigating to sign in",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "skipped"
});
formatter.step({
  "name": "I am on Citi home page",
  "keyword": "Given "
});
formatter.match({
  "location": "steps.Citi_Home.i_am_on_Citi_home_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I click on sign on",
  "keyword": "When "
});
formatter.match({
  "location": "steps.Citi_Home.i_click_on_sign_on()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I am navigated to \u0027https://uat02.citi.com/login\u0027",
  "keyword": "Then "
});
formatter.match({
  "location": "steps.Citi_Home.i_am_navigated_to(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "status": "skipped"
});
formatter.uri("file:features/citi_login.feature");
formatter.feature({
  "name": "Citi login page scenarios",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Citi login",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "skipped"
});
formatter.step({
  "name": "user is on login page",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "user enters valid username and password",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "user is navigated to \u0027https://uat2.online.citi.com/US/ag/mrc/dashboard\u0027",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "status": "skipped"
});
});
package com.herokuapp.restfulbooker;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HealthCheckTests extends BaseTest {
	
	@Test
	public void healthCheckTest() {
		
		given()
		.spec(spec)
	    .when()
		.get("/ping")
		.then()
		.assertThat()
		.statusCode(201);
		
	}
	
	@Test
	public void headersAndCookiesTest() {
		Header someHeader = new Header("Some header name", "Some header value");
		spec.header(someHeader);
		spec.header("headerName2", "headerValue2");
		
		Cookie someCookie = new Cookie.Builder("Some cookie name", "Some cookie value").build();
		spec.cookie(someCookie);
		
		Response response = RestAssured.given(spec)
				.cookie("Test cookie name", "Test cookie value")
				.header("Test header name", "Test header value")
				.log().all()
				.get("/ping");
		
		// Get headers
		Headers headers = response.getHeaders();
		System.out.println("Headers: "+headers);
		
		Header serverHeader1 = headers.get("Server");
		System.out.println(serverHeader1.getName()+": "+serverHeader1.getValue());
		
		System.out.println("Server: "+response.getHeader("Server"));
		
		// Get cookies
		Cookies cookies = response.getDetailedCookies();
		System.out.println("Cookies: "+cookies);
		
	}

}

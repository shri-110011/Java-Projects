package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	protected RequestSpecification spec;
	
	@BeforeMethod
	protected void setUp() {
		spec = new RequestSpecBuilder()
				.setBaseUri("https://restful-booker.herokuapp.com")
				.build();
	}
	
	protected Response createBooking() {
		// Create a JSON body
		JSONObject  body = new JSONObject();
		body.put("firstname", "Shrikant");
		body.put("lastname", "Anand");
		body.put("totalprice", 100);
		body.put("depositpaid", true);
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2022-09-14");
		bookingdates.put("checkout", "2022-09-30");
		
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Chicken Biryani");
		
		JSONObject jsonData = new JSONObject(
				 "{favColors: [red, blue, green]}"
				); 
		
		System.out.println(jsonData);
		// Get the response
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		
		response.print();
		return response;
	}

}

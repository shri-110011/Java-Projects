package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class PartialUpdateBookingTests extends BaseTest {
	
	@Test
	public void partialUpdateBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		System.out.println(responseCreate.getStatusCode());
		System.out.println(responseCreate.getContentType());
		System.out.println("Printing headers...");
		for(Header header: responseCreate.getHeaders())
			System.out.println(header);
		
		// Get bookingId of new booking
		int bookingId = responseCreate.jsonPath().getInt("bookingid");
		
		// Create a JSON body
		JSONObject  body = new JSONObject();
		body.put("firstname", "Siddharth");

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2022-09-20");
		bookingdates.put("checkout", "2022-09-30");
		
		body.put("bookingdates", bookingdates);
		
		// Update firstName and checkin
//		String newFirstName = "Siddharth",
//				checkin="2022-09-20";
		
		Response responsePartialUpdate = RestAssured.given()
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.contentType(ContentType.JSON)
				.body(body.toString())
				.patch("https://restful-booker.herokuapp.com/booking/"+bookingId);
		
		System.out.println("**************");
		responsePartialUpdate.print();
		
		StringBuilder sb = new StringBuilder(responsePartialUpdate.jsonPath().getString("bookingdates"));
//		sb.replace(0,1,"{").replace(sb.length()-1, sb.length(), "}");
		System.out.println(sb);
		JSONObject jsonData = new JSONObject();
		jsonData.put("bookingdates", sb.toString());
		System.out.println(jsonData);
		// Verify the response has status code as 200
		Assert.assertEquals(responsePartialUpdate.getStatusCode(), 200, "Status code should be 200 but it isn't.");
		
		// Verify firstName and checkin fields
		SoftAssert softAssert = new SoftAssert();
		
		String actualFirstName = responsePartialUpdate.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Siddharth", "Actual first name doesn't match the expected one.");
		
		String actualChekin = responsePartialUpdate.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualChekin, "2022-09-20", "Actual checkin date doesn't match the expected one.");
		
		softAssert.assertAll();
	}

}

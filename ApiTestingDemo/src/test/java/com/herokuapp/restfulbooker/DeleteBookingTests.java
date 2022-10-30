package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteBookingTests extends BaseTest {
	
	@Test
	public void deleteBooking() {
		// Create booking
		Response responseCreate = createBooking();
		System.out.println(responseCreate.getStatusCode());
		System.out.println(responseCreate.getContentType());
		System.out.println("Printing headers...");
		for(Header header: responseCreate.getHeaders())
			System.out.println(header);
		
		// Get bookingId of new booking
		int bookingId = responseCreate.jsonPath().getInt("bookingid");
		
		// Delete the specific booking
		Response responseDelete = 
				RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.delete("/booking/"+bookingId);
		
		System.out.println("**************");
		responseDelete.print();
		
		// Verify the response has status code as 201
		Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code should be 201 but it isn't.");
	
		Response responseGet = RestAssured.given().get("https://restful-booker.herokuapp.com/booking/"+bookingId);
		responseGet.print();
		
		Assert.assertEquals(responseGet.body().asString(), "Not Found", "Body should be 'Not Found' but it isn't.");
	}

}

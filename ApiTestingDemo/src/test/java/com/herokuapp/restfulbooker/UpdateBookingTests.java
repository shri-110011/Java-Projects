package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateBookingTests extends BaseTest {
	
	@Test
	public void updateBookingTest() {
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
		body.put("lastname", "Anand");
		body.put("totalprice", 90);
		body.put("depositpaid", true);
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2022-09-14");
		bookingdates.put("checkout", "2022-09-30");
		
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Chicken Biryani");
		
		// Update the booking
		Response responseUpdate = RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("admin", "password123")
				.contentType(ContentType.JSON)
				.body(body.toString())
				.put("/booking/"+bookingId);
		
		System.out.println("**************");
		responseUpdate.print();
		// Verify the response has status code as 200
		Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Status code should be 200 but it isn't.");
		
		// Verify all fields
		SoftAssert softAssert = new SoftAssert();
		
		String actualFirstName = responseUpdate.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Siddharth", "Actual first name doesn't match the expected one.");
		
		String actualLastName = responseUpdate.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Anand", "Actual last name doesn't match the expected one.");
		
		int actualPrice = responseUpdate.jsonPath().getInt("totalprice");
		softAssert.assertEquals(actualPrice, 90, "Actual price doesn't match the expected one.");
		
		boolean actualIsDepositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(actualIsDepositPaid, true, "Actual isDepositPaid doesn't match the expected one.");
		
		String actualChekin = responseUpdate.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualChekin, "2022-09-14", "Actual checkin date doesn't match the expected one.");
		
		String actualChekout = responseUpdate.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualChekout, "2022-09-30", "Actual checkout date doesn't match the expected one.");
		
		String actualAdditionalneeds = responseUpdate.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Chicken Biryani", "Actual additional needs doesn't match the expected one.");
		
		softAssert.assertAll();
		
		/* {
		 * "firstname":"James",
		 * "lastname":"Brown",
		 * "totalprice":111,
		 * "depositpaid":true,
		 * "bookingdates":{
		 * "checkin":"2018-01-01",
		 * "checkout":"2019-01-01"
		 * },
		 * "additionalneeds":"Breakfast"}
		 * 
		 */
	}
}

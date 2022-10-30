package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetBookingTests extends BaseTest {

	@Test(enabled = false)
	public void getBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		System.out.println(responseCreate.getStatusCode());
		System.out.println(responseCreate.getContentType());
		System.out.println("Printing headers...");
		for (Header header : responseCreate.getHeaders())
			System.out.println(header);
		
		// Set path parameter
		spec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

		// Get response with booking id
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");

		response.print();

		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");

		// Verify all fields
		SoftAssert softAssert = new SoftAssert();

		String actualFirstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Shrikant", "Actual first name doesn't match the expected one.");

		String actualLastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Anand", "Actual last name doesn't match the expected one.");

		int actualPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(actualPrice, 100, "Actual price doesn't match the expected one.");

		boolean actualIsDepositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertEquals(actualIsDepositPaid, true, "Actual isDepositPaid doesn't match the expected one.");

		String actualChekin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualChekin, "2022-09-14", "Actual check in date doesn't match the expected one.");

		String actualChekout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualChekout, "2022-09-30", "Actual check out date doesn't match the expected one.");

		String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Chicken Biryani",
				"Actual additional needs doesn't match the expected one.");

		softAssert.assertAll();
		/*
		 * { "firstname":"James", "lastname":"Brown", "totalprice":111,
		 * "depositpaid":true, "bookingdates":{ "checkin":"2018-01-01",
		 * "checkout":"2019-01-01" }, "additionalneeds":"Breakfast"}
		 * 
		 */

	}
	
	@Test
	public void getBookingXMLTest() {
		// Create booking
		Response responseCreate = createBooking();
		System.out.println(responseCreate.getStatusCode());
		System.out.println(responseCreate.getContentType());
		System.out.println("Printing headers...");
		for (Header header : responseCreate.getHeaders())
			System.out.println(header);
		
		// Set path parameter
		spec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

		// Get response with booking id
		Header xml = new Header("Accept", "application/xml");
		spec.header(xml);
		Response response = RestAssured.given(spec).get("/booking/{bookingId}");

		response.print();

		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");

		// Verify all fields
		SoftAssert softAssert = new SoftAssert();

		String actualFirstName = response.xmlPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Shrikant", "Actual first name doesn't match the expected one.");

		String actualLastName = response.xmlPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Anand", "Actual last name doesn't match the expected one.");

		int actualPrice = response.xmlPath().getInt("booking.totalprice");
		softAssert.assertEquals(actualPrice, 100, "Actual price doesn't match the expected one.");

		boolean actualIsDepositPaid = response.xmlPath().getBoolean("booking.depositpaid");
		softAssert.assertEquals(actualIsDepositPaid, true, "Actual isDepositPaid doesn't match the expected one.");

		String actualChekin = response.xmlPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualChekin, "2022-09-14", "Actual check in date doesn't match the expected one.");

		String actualChekout = response.xmlPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualChekout, "2022-09-30", "Actual check out date doesn't match the expected one.");

		String actualAdditionalneeds = response.xmlPath().getString("booking.additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Chicken Biryani",
				"Actual additional needs doesn't match the expected one.");

		softAssert.assertAll();

	}

}

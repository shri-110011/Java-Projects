package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class CreateBookingTests extends BaseTest {
	
//	@Test
	public void createBookingTests() {
		Response response = createBooking();
		System.out.println(response.getStatusCode());
		System.out.println(response.getContentType());
		System.out.println("Printing headers...");
		for(Header header: response.getHeaders())
			System.out.println(header);
		
		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");
		
		// Verify all fields
		SoftAssert softAssert = new SoftAssert();
		
		String actualFirstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Shrikant", "Actual first name doesn't match the expected one.");
		
		String actualLastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Anand", "Actual last name doesn't match the expected one.");
		
		int actualPrice = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(actualPrice, 100, "Actual price doesn't match the expected one.");
		
		boolean actualIsDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertEquals(actualIsDepositPaid, true, "Actual isDepositPaid doesn't match the expected one.");
		
		String actualChekin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualChekin, "2022-09-14", "Actual checkin date doesn't match the expected one.");
		
		String actualChekout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualChekout, "2022-09-30", "Actual checkout date doesn't match the expected one.");
		
		String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
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
	
	@Test
	public void createBookingWithPOJOTests() {
		// Create booking with POJO
		BookingDates bookingdates = new BookingDates("2022-10-12", "2022-10-31");
		Booking booking = new Booking("Shrikant", "Anand", "Chicken Biryani", 200, true, bookingdates);
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(booking)
				.post("https://restful-booker.herokuapp.com/booking");
		
		response.print();
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getContentType());
		System.out.println("Printing headers...");
		for(Header header: response.getHeaders())
			System.out.println(header);
		
		BookingId bookingid = response.as(BookingId.class);
		
		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");
		
		System.out.println("Request booking: "+booking);
		System.out.println("Reesponse booking: "+bookingid.getBooking());
		
		// Verify all fields
		Assert.assertEquals(bookingid.getBooking().toString(), booking.toString());
		
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

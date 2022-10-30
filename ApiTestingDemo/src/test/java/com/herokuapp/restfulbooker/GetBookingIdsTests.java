package com.herokuapp.restfulbooker;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTests extends BaseTest {

	@Test
	public void getBookingIdsWithoutFilterTest() {

		// Get response with booking ids
		Response response = RestAssured.given(spec).get("/booking");

		response.print();

		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");

		/*
		 * Verify that there is at least one booking id in the response.
		 */
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");

		System.out.println("Number of bookingIds: " + bookingIds.size());
		System.out.println(bookingIds.get(0));
		Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty but it shouldn't be.");

	}

	@Test
	public void getBookingIdsWithFilterTest() {
		
		spec.queryParam("firstname", "Shrikant");
		spec.queryParam("lastname", "Anand");
		
		// Get response with booking ids
		Response response = RestAssured
				.given(spec)
				.get("/booking");

		response.print();

		// Verify the response has status code as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it isn't.");

		/*
		 * Verify that there is at least one booking id in the response.
		 */
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");

		System.out.println("Number of bookingIds: " + bookingIds.size());
		System.out.println(bookingIds.get(0));
		Assert.assertFalse(bookingIds.isEmpty(), "List of bookingIds is empty but it shouldn't be.");
	}

}

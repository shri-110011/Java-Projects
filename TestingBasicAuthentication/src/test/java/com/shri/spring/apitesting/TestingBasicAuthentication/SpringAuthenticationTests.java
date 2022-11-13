package com.shri.spring.apitesting.TestingBasicAuthentication;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringAuthenticationTests extends BaseTest
{
    @Test
    public void basicAuthenticationTest()
    {
    	Response response= RestAssured.given(spec)
				.auth()
				.preemptive()
				.basic("John", "test123")
				.get();
    	System.out.println(response.asPrettyString());
    }
}

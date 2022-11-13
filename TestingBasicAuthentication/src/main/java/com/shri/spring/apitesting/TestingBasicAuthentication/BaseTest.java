package com.shri.spring.apitesting.TestingBasicAuthentication;

import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest
{
	protected RequestSpecification spec;
	
	@BeforeMethod
	protected void setUp() {
		spec = new RequestSpecBuilder()
				.setBaseUri("http://localhost:9001/spring-security-demo-02-basic-security")
				.build();
	}
	
}

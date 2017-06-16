package com.flight.Webservices1;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RESTTester {

	@Test(priority = 1)
	public void verifyHttpStatusCode() {

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		System.out.println(RestAssured.get("/users").getStatusCode());
		Assert.assertEquals(RestAssured.get("/users").getStatusCode(), 200,
				"Status code of the obtained response is not 200 OK");
		

	}

	@Test(priority = 2)
	public void verifyHttpResponseHeader() {
		Assert.assertTrue(RestAssured.get("/users").getHeaders().hasHeaderWithName("content-type"),
				"The content-type Header is not exists in the obtained reponse body");
		System.out.println(RestAssured.get("/users").getHeaders().hasHeaderWithName("content-type"));
		Assert.assertEquals(RestAssured.get("/users").getHeader("content-type"), "application/json; charset=utf-8",
				"The value of the content-type Header value is not matched");
		System.out.println(RestAssured.get("/users").getHeader("content-type")+" "+"The value of the content-type Header value is  matched");

	}

	@Test(priority = 3)
	public void verifyHttpResponseBody() {

		System.out
				.println(RestAssured.get("/users").then().contentType(ContentType.JSON).extract().path("list.size()"));

		Assert.assertEquals(
				RestAssured.get("/users").then().contentType(ContentType.JSON).extract().path("list.size()"), 10,
				"Number of users is not matched");

	}

}// Class

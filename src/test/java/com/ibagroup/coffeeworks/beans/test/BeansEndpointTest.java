package com.ibagroup.coffeeworks.beans.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BeansEndpointTest {

	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
	}
	
    @Test
    public void testBeansFetchesSuccess() {
        get("/coffee-works/rest/beans/2")
        .then()
        .body("id", equalTo(2))
        .body("name", equalTo("Robusta"));
    }
    
    @Test
    public void testBeansPostSuccess() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Bean_test");

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("/coffee-works/rest/beans");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }
    
    @Test
    public void testBeansDeleteSuccess() {
        given().pathParam("id", 38)
        .when().delete("/coffee-works/rest/beans/{id}")
        .then().statusCode(204);
    }
	
}

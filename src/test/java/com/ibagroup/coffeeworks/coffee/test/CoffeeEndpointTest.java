package com.ibagroup.coffeeworks.coffee.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Endpoint REST Test for Connection, GET, POST, DELETE
 * 
 * @author IBA Group
 * @since 2018
 *
 */

// https://semaphoreci.com/community/tutorials/testing-rest-endpoints-using-rest-assured

// https://stackoverflow.com/questions/45509669/how-to-send-nested-json-objects-inside-a-request-body

public class CoffeeEndpointTest {

	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
	}
	
    @Test
    public void testCoffeeFetchesSuccess() {
        get("/coffee-works/rest/coffee/2")
        .then()
        .body("id", equalTo(2))
        .body("name", equalTo("Double Espresso"))
        .body("location", equalTo("Italia"))
        .body("description", equalTo("A double espresso (aka “Doppio”) is just that, two espresso shots in one cup."))
        .body("stars", equalTo(4))
        .body("beans.id", equalTo(1));
    }

    /**
     * <p>
     * Additional method for create nested JSON. Here we generate a bean ID and POST it in the testCoffeePostSuccess()
     * </p>
     * 
     * <p>
     * For more information visit <a href="https://stackoverflow.com/questions/45509669/how-to-send-nested-json-objects-inside-a-request-body">Stackoverflow</a>
     * </p>
     * 
     * @return beans_id
     * @throws JSONException
     */
    private JSONObject beanId() throws JSONException {
        JSONObject params = new JSONObject();
        params.put( "id", 2);
        return params;
    }
    
    @Test
    public void testCoffeePostSuccess() {
        String someRandomString = String.format("%1$TH%1$TM%1$TS", new Date());

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Coffee_test");
        requestBody.put("location", "Location_test");
        requestBody.put("description", "Description_test");
        requestBody.put("stars", someRandomString);
        requestBody.put("beans", beanId());

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("/coffee-works/rest/coffee");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }
    
    @Test
    public void testCoffeeDeleteSuccess() {
        given().pathParam("id", 107)
        .when().delete("/coffee-works/rest/coffee/{id}")
        .then().statusCode(204);

    }
    
}

package com.ibagroup.coffeeworks.connection.test;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

/**
 * Test case for connection. Tests if application has started on server successfully
 * 
 * @author IBA Group
 * @since 2018
 *
 */

public class CoffeeWorksConnectionTest {

	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

	 @Test
     public void testCoffeeWorksConnectionSuccess() {
         given().when().get("/coffee-works/").then().statusCode(200);
     }

}

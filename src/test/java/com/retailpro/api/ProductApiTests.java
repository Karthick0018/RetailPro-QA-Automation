package com.retailpro.api;

import com.retailpro.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductApiTests {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigReader.get("api.base.url");
    }

    @Test(description = "GET /products returns a non-empty list")
    public void getAllProducts() {
        given().when().get("/products")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].title", not(emptyOrNullString()));
    }

    @Test(description = "GET /products/1 returns the product with matching id")
    public void getSingleProduct() {
        given().when().get("/products/1")
                .then().statusCode(200)
                .body("id", equalTo(1))
                .body("price", greaterThan(0f))
                .body("category", not(emptyOrNullString()));
    }

    @Test(description = "GET /products/categories returns known categories")
    public void getCategories() {
        given().when().get("/products/categories")
                .then().statusCode(200)
                .body("size()", greaterThan(0))
                .body("", hasItem("electronics"));
    }

    @Test(description = "GET /products?limit=3 honours the limit")
    public void getProductsWithLimit() {
        given().queryParam("limit", 3)
                .when().get("/products")
                .then().statusCode(200)
                .body("size()", equalTo(3));
    }

    @Test(description = "POST /products echoes the created product with an id")
    public void createProduct() {
        Map<String, Object> body = Map.of(
                "title", "RetailPro Test Item",
                "price", 19.99,
                "description", "Created by automation",
                "category", "electronics",
                "image", "https://example.com/item.png");

        given().contentType(ContentType.JSON).body(body)
                .when().post("/products")
                .then().statusCode(anyOf(is(200), is(201)))
                .body("id", notNullValue());
    }
}

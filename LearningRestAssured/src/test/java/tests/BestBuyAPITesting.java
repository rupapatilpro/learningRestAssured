package tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BestBuyAPITesting {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://localhost/";
		RestAssured.port = 3030;
		
	}
	
	@Test
	public void verifyGetProduct() {
		
		
		RestAssured.given().when().get("/products").then().statusCode(200);
	}
	
	
	@Test
	public void verifyGetProductwithLimit() {
		
		
		RestAssured.given().when().queryParam("$limit", 5).get("/products").then().log().all().statusCode(200);
	}
	
	@Test
	public void verifyGetProductwithSpecificId() {
		
		
		RestAssured.given().when().param("id", 43900). get("/products").then().log().all().statusCode(200);
	}
	
	@Test
	public void verifyPostProduct() {
		String requestPayload = "{\n"
				+ "  \"name\": \"Samsung Mobile \",\n"
				+ "  \"type\": \"Mobile\",\n"
				+ "  \"price\": 200,\n"
				+ "  \"shipping\": 80,\n"
				+ "  \"upc\": \"Mobile\",\n"
				+ "  \"description\": \"string\",\n"
				+ "  \"manufacturer\": \"Samsung\",\n"
				+ "  \"model\": \"string\",\n"
				+ "  \"url\": \"string\",\n"
				+ "  \"image\": \"string\"\n"
				+ "}";
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(requestPayload)
		.when().post("/products")
		.then().statusCode(201).log().all();

		
	}
	
	@Test
	public void verifyPostProductwithPayloadAsObject() {
		
		Map<String, Object> requestPayload = new HashMap<>();
		
		requestPayload.put("name", "iPhone 12");
		requestPayload.put("type", "iPhone Mobile");
		requestPayload.put("upc", "iPhone 12");
		requestPayload.put("price", 500);
		requestPayload.put("shipping", 90);
		requestPayload.put("model", "iPhone 12");
		requestPayload.put("description", "iPhone 12");
		
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(requestPayload)
		.when().post("/products")
		.then().statusCode(201).log().all();

		
	}
	
	@Test
	public void verifyUpdateProductwithPayloadAsObject() {
		
		Map<String, Object> requestPayload = new HashMap<>();
		
		requestPayload.put("name", "iPhone 12");
		requestPayload.put("type", "iPhone Mobile");
		requestPayload.put("upc", "iPhone 12");
		requestPayload.put("price", 500);
		requestPayload.put("shipping", 90);
		requestPayload.put("model", "iPhone 12");
		requestPayload.put("description", "iPhone 12");
		
		
	int productId =	RestAssured.given().contentType(ContentType.JSON)
		.body(requestPayload)
		.when().post("/products")
		.then().extract().path("id");
	
	requestPayload.put("name", "iPhone 14");
	requestPayload.put("type", "iPhone Mobile");
	requestPayload.put("upc", "iPhone 12");
	requestPayload.put("price", 500);
	requestPayload.put("shipping", 90);
	requestPayload.put("model", "iPhone 12");
	requestPayload.put("description", "iPhone 12");
	
	RestAssured.given().contentType(ContentType.JSON)
	.body(requestPayload)
	.when().put("/products/" + productId)
	.then().statusCode(200).log().all();

		
	}
	
	
	@Test
	public void verifyDeleteProduct() {
		
		Map<String, Object> requestPayload = new HashMap<>();
		
		requestPayload.put("name", "iPhone 12");
		requestPayload.put("type", "iPhone Mobile");
		requestPayload.put("upc", "iPhone 12");
		requestPayload.put("price", 500);
		requestPayload.put("shipping", 90);
		requestPayload.put("model", "iPhone 12");
		requestPayload.put("description", "iPhone 12");
		
		
	int productId =	RestAssured.given().contentType(ContentType.JSON)
		.body(requestPayload)
		.when().post("/products")
		.then().extract().path("id");
	
	
	
	RestAssured.given().contentType(ContentType.JSON)
	.body(requestPayload)
	.when().delete("/products/" + productId)
	.then().statusCode(200).log().all();
	
	RestAssured.given().when().get("/products/"+productId).then().statusCode(404);

		
	}
	
	
	

	
}
		

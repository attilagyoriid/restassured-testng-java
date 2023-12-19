package com.ag.restassured.responseextraction.rootpath;


import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.util.HashMap;


import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class RootPathExample2 {
	static HashMap<String,Object> parameters = new HashMap<String,Object>();

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		RestAssured.rootPath="query.results.rate";
		
		parameters.put("q","select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
		parameters.put("format","json");
		parameters.put("env","store://datatables.org/alltableswithkeys");

	}
	@AfterClass
	public static void tearDown(){
		RestAssured.reset();
	}

		@Test
		public void test002() {
			given()
			.params(parameters)
			.when()
			.get("/yql")
			.then()
			.body("Name", hasItem("USD/INR"));
		}


	@Test
	public void test003() {
		given()
		.params(parameters)
		.when()
		.get("/yql")
		.then()
		.body("Name", hasItems("USD/INR","USD/THB","USD/BRL"));
	}



	@Test
	public void test004() {
		given()
		.params(parameters)
		.when()
		.get("/yql")
		.then()
		.body("Name", hasItem("USD/INR"))
		.body("id",hasItem("USDCAD"))
		.body("Name", hasItems("USD/INR","USD/THB","USD/BRL"));
		
	}
}

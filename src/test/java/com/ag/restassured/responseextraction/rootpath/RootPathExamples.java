package com.ag.restassured.responseextraction.rootpath;



import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class RootPathExamples {
	static HashMap<String,Object> parameters = new HashMap<String,Object>();

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		
		parameters.put("q","select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
		parameters.put("format","json");
		parameters.put("env","store://datatables.org/alltableswithkeys");

	}
	
	
	// 1 Adding multiple assertions in single test
	
	@Test
	public void test001() {
		given().params(parameters)
		.when()
		.get("/yql")
		.then()
		.root("query.results.rate")
		
		.body("Name", hasItem("USD/INR"))
		.body("Name", hasItems("USD/INR","USD/THB","USD/BRL"))
		.body("id",hasItem("USDCAD"))
		
		.root("query")
		
		
		.body("count",lessThan(10))
		.body("count",greaterThan(4));
		
	}


}

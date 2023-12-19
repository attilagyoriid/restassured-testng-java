package com.ag.restassured.fileupload;


import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUploadLoadExample {


	@Test
	public void uploadFileExample(){

	String apiKey="";
	
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"dancing_banana.gif");
		System.out.println(inputFile.length());
		String endpoint = "https://sandbox.zamzar.com/v1/jobs";
		
		given()
		.auth()
		.basic(apiKey,"")
		.multiPart("source_file",inputFile)
		.multiPart("target_format","png")
		.when()
		.post(endpoint)
		.then()
		.log()
		.all();
	}
}

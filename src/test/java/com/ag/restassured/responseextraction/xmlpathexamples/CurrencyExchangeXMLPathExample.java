package com.ag.restassured.responseextraction.xmlpathexamples;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.with;

public class CurrencyExchangeXMLPathExample {


    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    @Test
    public void test001() {
        String count = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.@yahoo:count");

        System.out.println("The count is: " + count);

    }


    @Test
    public void test002() {

        String lang = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.@yahoo:lang");

        System.out.println("The lang is: " + lang);
    }


    @Test
    public void test003() {

        String name = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate[0].Name");

        System.out.println("The name is: " + name);
    }

    @Test
    public void test004() {
        String xml = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();

        String valuesUnderRate = with(xml).get("query.results.rate").toString();

        System.out.println("The values under rate are: " + valuesUnderRate);
    }


    @Test
    public void test005() {
         given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate");
//        System.out.println("The size of rate is: " + sizeOfNode.size());
    }


    @Test
    public void test006() {
        String xml = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String names = with(xml).get("query.results.rate.findAll{it.Name}.Name").toString();

        System.out.println("The names are: " + names);

    }

    @Test
    public void test007() {

        String xml = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String values = with(xml).get("**.findAll{it.Name=='USD/AUD'}").toString();

        System.out.println("The values are: " + values);
    }

    @Test
    public void test008() {
        String xml = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String values = with(xml).get("**.findAll{it.@id=='USDINR'}.Rate").toString();
        System.out.println("The Rate is " + values);
    }

}

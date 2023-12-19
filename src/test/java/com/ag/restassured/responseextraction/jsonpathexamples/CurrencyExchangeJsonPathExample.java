package com.ag.restassured.responseextraction.jsonpathexamples;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class CurrencyExchangeJsonPathExample {


    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    @Test
    public void test001() {
        int count = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.count");
        System.out.println("The value of count is:" + count);
    }


    @Test
    public void test002() {

        String lang = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.lang");
        System.out.println("The value of lang is:" + lang);
    }


    @Test
    public void test003() {

        String name = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate[0].Name");
        System.out.println("The value of name is:" + name);
    }


    @Test
    public void test004() {


        List<String> rateValues = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate");
        System.out.println("The rate values are : " + rateValues);
        System.out.println("The size of rate is : " + rateValues.size());

    }


    @Test
    public void test005() {
        int sizeOfRate = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.size()");
        System.out.println("The size of rate is : " + sizeOfRate);
    }


    @Test
    public void test006() {

        List<String> names = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.Rate");
        System.out.println("The values of Names are : " + names);

    }


    @Test
    public void test007() {

        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.Name=='USD/BRL'}");
        System.out.println("The values  are : " + values);
    }

    @Test
    public void test008() {

        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\",\"USDEUR\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.Name=='USD/EUR'}");
        System.out.println("The values  for Name USD/EUR are : " + values);
    }

    @Test
    public void test009() {

        Response response = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\",\"USDEUR\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql");

        List<String> names = response
                .then()
                .extract()
                .path("query.results.rate.findAll{it.Rate>'30'}.Name");

    }

    @Test
    public void test010() {
        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\",\"USDEUR\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.id==~/USDB.*/}");
        System.out.println("The values  that start with USDB are: " + values);

    }

    @Test
    public void test011() {

        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\",\"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\",\"USDEUR\")")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.id==~/.*UD/}");
        System.out.println("The values  that end with id: UD are: " + values);
    }

}

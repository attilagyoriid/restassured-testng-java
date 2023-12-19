package com.ag.restassured.responseextraction.assertions;


import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.apache.http.HttpStatus;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AddingAssertions {
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeMethod
    public static void init() {
        HttpClientConfig clientConf = new HttpClientConfig();
        clientConf.setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000)
                .setParam(CoreConnectionPNames.SO_TIMEOUT, 3000);
        RestAssured.config().httpClient(clientConf);
        parameters.put("page", 2);

    }


    public void test001() {
        given()
                .params(parameters)
                .when()
                .get("/api/users")
                .then().log().all().assertThat().statusCode(HttpStatus.SC_OK)
                .body("data", hasSize(6));


    }


    public void test002() {
        given()
                .params(parameters)
                .when()
                .get("https://reqres.in/api/users")
                .then().log().all()
                .body("total", is(equalTo(12)));
    }


    public void test003() {
        given()
                .params(parameters)
                .when()
                .get("https://reqres.in/api/users")
                .then().log().all()
                .body("data.email", contains("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in",
                        "eve.holt@reqres.in", "charles.morris@reqres.in", "tracey.ramos@reqres.in"));
    }


    @Test(groups = {"refactored"}, dataProvider = "user-provider")
    public void test004(String userID, String expectedFirstName) {
        String firstName = given()
                .pathParam("userID", userID)
                .when()
                .get("https://reqres.in/api/users/{userID}")
                .then()
                .extract().response()
                .path("data.first_name");
        assertThat(firstName, is(equalTo(expectedFirstName)));
    }

    @DataProvider(name = "user-provider")
    public Object[][] userDataProvider() {
        return new Object[][]{{"2", "Janet"}, {"3", "Emma"}};
    }


}

package com.ag.restassured.filedownload;


import com.ag.restassured.utils.FileUtil;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FileDownLoadExample {

    @Test(groups = { "refactored" })
    public void verifyDownloadedFile() throws UnsupportedEncodingException {

        String url = "https://unsplash.com/photos/OqtafYT5kTw/download?ixid=M3wxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNzAyOTY3MzQzfA&force=true&w=1920";

        String expectedResource = "laptop.jpg";
        File inputFile = FileUtil.getInstance().getFileFromResources(expectedResource);


        int expectedSize = (int)inputFile.length();


        byte[] actualValue = given()
                .when()
                .get(url)
                .then()
                .extract()
                .asByteArray();

        assertThat("The size of the actual file is: " + actualValue.length + " bytes",actualValue.length , equalTo(expectedSize));

    }
}

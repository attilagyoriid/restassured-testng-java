package com.ag.restassured.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileUtil {
    private static FileUtil INSTANCE = null;

    private FileUtil() {
    }

    public static synchronized FileUtil getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new FileUtil();

        return INSTANCE;
    }

    public File getFileFromResources(String fileName) throws UnsupportedEncodingException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(URLDecoder.decode(classLoader.getResource(fileName).getFile(), "UTF-8"));
    }
}

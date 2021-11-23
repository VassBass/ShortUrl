package com.vassbassapp.ShortUrl.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlConverterTest {

    @Test
    void isUrl() {
        String url = "https://mvnrepository.com/artifact/junit/junit/4.13.2";
        String notUrl = "Not url";

        assertTrue(UrlConverter.isUrl(url));
        assertFalse(UrlConverter.isUrl(notUrl));
    }
}
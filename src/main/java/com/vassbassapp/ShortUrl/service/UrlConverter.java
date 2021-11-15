package com.vassbassapp.ShortUrl.service;

import org.apache.commons.text.RandomStringGenerator;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlConverter {

    public static String createShortUrl(int lengthOfShortUrl){
        if (lengthOfShortUrl == 0){
            lengthOfShortUrl = 1;
        }else if (lengthOfShortUrl < 0){
            lengthOfShortUrl = Math.abs(lengthOfShortUrl);
        }
        RandomStringGenerator generator = new RandomStringGenerator.Builder().filteredBy(UrlConverter::isLetterOrDigit).build();
        return "http://localhost:8080/" + generator.generate(lengthOfShortUrl);
    }

    private static boolean isLetterOrDigit(int point){
        return ('a' <= point && point <= 'z')
                || ('A' <= point && point <= 'Z')
                || ('0' <= point && point <= '9')
                || ('+' == point)
                || ('_' == point)
                || ('-' == point);
    }

    public static boolean isUrl(String url){
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}

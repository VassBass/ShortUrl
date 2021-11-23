package com.vassbassapp.ShortUrl.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UrlRepository {

    private final HashMap<String, String> urlList;

    public UrlRepository(){
        this.urlList = new HashMap<>();
    }

    public void addUrl(String longUrl, String shortUrl){
        this.urlList.put(shortUrl, longUrl);
    }

    public String getLongUrl(String shortUrl){
        return urlList.get(shortUrl);
    }
}

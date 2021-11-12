package com.vassbassapp.ShortUrl.model;

import com.vassbassapp.ShortUrl.service.UrlConverter;

public class UrlKeeper {
    private String longUrl, shortUrl;
    private final int lengthOfShortUrl;

    public UrlKeeper(){
        this.lengthOfShortUrl = 6;
    }

    public UrlKeeper(String longUrl, int lengthOfShortUrl){
        this.longUrl = longUrl;
        this.lengthOfShortUrl = lengthOfShortUrl;
        this.shortUrl = UrlConverter.createShortUrl(lengthOfShortUrl);
    }

    public String getLongUrl(){
        return this.longUrl;
    }
    public String getShortUrl(){
        return this.shortUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}

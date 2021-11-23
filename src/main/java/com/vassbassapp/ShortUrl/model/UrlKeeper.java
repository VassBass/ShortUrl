package com.vassbassapp.ShortUrl.model;

public class UrlKeeper {
    private String longUrl, shortUrl;

    public UrlKeeper(){}
    public UrlKeeper(String longUrl, String shortUrl){
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public void setLongUrl(String url){
        this.longUrl = url;
    }

    public String getLongUrl(){
        return this.longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return this.shortUrl;
    }
}

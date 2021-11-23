package com.vassbassapp.ShortUrl.service;

import org.springframework.stereotype.Service;
import com.vassbassapp.ShortUrl.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository){
        this.repository = repository;
    }

    public void addUrl(String longUrl, String shortUrl){
        this.repository.addUrl(longUrl, shortUrl);
    }

    public String getLongUrl(String shortUrl){
        return this.repository.getLongUrl(shortUrl);
    }
}

package com.vassbassapp.ShortUrl.service;

import com.vassbassapp.ShortUrl.model.UrlKeeper;
import org.springframework.stereotype.Service;
import com.vassbassapp.ShortUrl.repository.UrlRepository;

import java.util.List;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository){
        this.repository = repository;
    }

    public UrlKeeper addUrl(String longUrl, int lengthOfShortUrl){
        return this.repository.addUrl(longUrl, lengthOfShortUrl);
    }

    public List<UrlKeeper>getAllUrl(){
        return this.repository.getAllUrl();
    }

    public String getLongUrl(String shortUrl){
        return this.repository.getLongUrl(shortUrl);
    }
}

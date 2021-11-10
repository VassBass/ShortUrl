package model;

import org.jetbrains.annotations.NotNull;
import service.UrlConverter;

public class UrlKeeper {
    @NotNull
    private final String longUrl, shortUrl;

    public UrlKeeper(@NotNull String longUrl, int lengthOfShortUrl){
        this.longUrl = longUrl;
        this.shortUrl = UrlConverter.createShortUrl(lengthOfShortUrl);
    }

    public @NotNull String getLongUrl(){
        return this.longUrl;
    }

    public @NotNull String getShortUrl(){
        return this.shortUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }else {
            UrlKeeper o = (UrlKeeper) obj;
            return this.shortUrl.equals(o.getShortUrl());
        }
    }
}

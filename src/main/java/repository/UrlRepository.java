package repository;

import model.UrlKeeper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UrlRepository {

    private final List<UrlKeeper> urlList;

    public UrlRepository(){
        this.urlList = new ArrayList<>();
    }

    public UrlKeeper addUrl(String longUrl, int lengthOfShortUrl){
        while (true){
            boolean exist = false;
            UrlKeeper urlKeeper = new UrlKeeper(longUrl, lengthOfShortUrl);
            for (UrlKeeper keeper : this.urlList){
                if (urlKeeper.equals(keeper)){
                    exist = true;
                    break;
                }
            }
            if (!exist){
                this.urlList.add(urlKeeper);
                return urlKeeper;
            }
        }
    }

    public List<UrlKeeper> getAllUrl(){
        return this.urlList;
    }

    public String getLongUrl(String shortUrl){
        for (UrlKeeper urlKeeper : urlList){
            if (urlKeeper.getShortUrl().equals(shortUrl)){
                return urlKeeper.getLongUrl();
            }
        }
        return null;
    }
}

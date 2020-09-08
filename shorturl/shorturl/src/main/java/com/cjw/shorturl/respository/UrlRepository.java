package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.Url;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UrlRepository {
    Url findUrlOne(Long urlId);

    //void registerUrl(Url url);
    void saveUrl(Url url);

    Url selectOriginalUrl(Long urlId);

    List<Url> registerUrlAccess(Long urlId, String link);

    boolean getBanUrl(String url);
}

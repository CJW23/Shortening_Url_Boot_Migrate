package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UrlRepository {
    Url findUrl(Long urlId);

    //void registerUrl(Url url);
    void save(Url url);

    Url selectOriginalUrl(Long urlId);

    void saveUrlAccess(AccessUrl accessUrl);

    boolean getBanUrl(String url);
}

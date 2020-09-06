package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.Url;

import java.util.List;

public interface UrlRepository {

    Url findOne(int urlId);

    void registerUrl(Url url);
    Url selectOriginalUrl(int urlId);
    List<Url> registerUrlAccess(int urlId, String link);
    boolean getBanUrl(String url);
}

package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.Url;

public interface MainService {
    String getOriginalUrl(String path);
    void urlAccess(String path, String link);
    Url makeUrl(Url url) throws Exception;

}

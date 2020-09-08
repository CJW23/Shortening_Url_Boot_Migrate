package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.Url;

import java.util.Map;

public interface MainService {
    String getOriginalUrl(String path);
    void urlAccess(String path, String link);
    Url makeUrl(Url url) throws Exception;
    Map<String, String> registerUrl(Url url) throws Exception;
}

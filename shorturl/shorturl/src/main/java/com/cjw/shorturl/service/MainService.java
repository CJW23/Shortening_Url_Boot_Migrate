package com.cjw.shorturl.service;

public interface MainService {
    String getOriginalUrl(String path);
    void urlAccess(String path, String link);
    String makeUrl(String originalUrl);

}

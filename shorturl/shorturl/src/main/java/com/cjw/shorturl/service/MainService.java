package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;

import java.util.Map;

public interface MainService {
    String findOriginalUrl(Long id);
    Url findUrl(Long id);
    void saveUrlAccess(AccessUrl accessUrl);
    Url makeUrl(Url url) throws Exception;
    Map<String, String> saveUrl(Url url) throws Exception;
}

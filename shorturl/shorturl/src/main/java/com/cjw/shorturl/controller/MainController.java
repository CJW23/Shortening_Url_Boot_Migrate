package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService mainService;

    /**
     * 단축 URL 생성
     * @param url
     * @return
     * @throws Exception
     */
    @PostMapping("/create")
    public Map<String, String> create(Url url) throws Exception {
        try{
            return mainService.registerUrl(url);
        } catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("originalUrl", url.getOriginalUrl());
            map.put("shortUrl", e.getMessage());
            return map;
        }

    }
}

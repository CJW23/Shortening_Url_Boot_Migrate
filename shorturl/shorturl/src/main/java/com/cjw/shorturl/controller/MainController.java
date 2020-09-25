package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.service.MainServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainServiceImpl mainService;

    /**
     * 단축 URL 생성
     * @param url
     * @return
     * @throws Exception
     */
    @PostMapping("/guest/create")
    public Map<String, String> guestCreate(Url url) throws Exception {
        try{
            log.info(url.getOriginalUrl());
            return mainService.saveUrl(url);
        } catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("originalUrl", url.getOriginalUrl());
            map.put("shortUrl", e.getMessage());
            return map;
        }
    }
}

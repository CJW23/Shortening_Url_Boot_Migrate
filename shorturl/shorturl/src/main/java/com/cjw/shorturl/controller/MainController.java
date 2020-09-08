package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Url create(Url url) throws Exception {
        try{
            return mainService.makeUrl(url);
        } catch (Exception e){
            log.info(e.getMessage());
            return null;
        }

    }
}

package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.service.UrlServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlServiceImpl urlService;

    /**
     * 단축URL 원본 URL로 Redirect
     * @param path
     * @return
     */
    @GetMapping("/{path}")
    public String redirectUrl(@PathVariable("path") String path){
        Long id = Base62.decodeToLong(path);
        Url url = urlService.findUrl(id);
        log.info("awd " + url.getOriginalUrl());
        AccessUrl accessUrl = new AccessUrl();
        accessUrl.setUrl(url);
        accessUrl.setBeforeUrl(null);   //추후 구현

        urlService.saveUrlAccess(accessUrl);
        return "redirect:" + url.getOriginalUrl();
    }


    /*@PostMapping("/user/create")
    public void userCreate(Url url) throws Exception{

    }*/
}

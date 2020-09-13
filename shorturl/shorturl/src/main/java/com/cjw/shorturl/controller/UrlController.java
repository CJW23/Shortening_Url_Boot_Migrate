package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UrlController {
    private final MainService mainService;

    @GetMapping("/{path}")
    public String redirectUrl(@PathVariable("path") String path){
        Long id = Base62.decodeToLong(path);
        Url url = mainService.findUrl(id);

        AccessUrl accessUrl = new AccessUrl();
        accessUrl.setUrl(url);
        accessUrl.setBeforeUrl(null);   //추후 구현

        mainService.saveUrlAccess(accessUrl);
        return "redirect:" + url.getOriginalUrl();
    }
}

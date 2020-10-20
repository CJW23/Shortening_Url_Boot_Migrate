package com.cjw.shorturl.controller;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.exception.ShortUrlNotFoundException;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.service.UrlServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlServiceImpl urlService;

    /**
     * 단축URL 원본 URL로 Redirect
     *
     * @param path
     * @return
     */
    @GetMapping("/a/{path}")
    public String redirectUrl(@PathVariable("path") String path) throws ShortUrlNotFoundException {
        Url url = urlService.findUrl(Base62.decodeToLong(path));
        if (url == null) {
            throw new ShortUrlNotFoundException("잘못된 단축 URL입니다");
        }
        AccessUrl accessUrl = new AccessUrl();
        accessUrl.setUrl(url);
        accessUrl.setBeforeUrl(null);   //추후 구현
        urlService.saveUrlAccess(accessUrl);
        urlService.upUrlCount(url);
        return "redirect:" + url.getOriginalUrl();
    }
}

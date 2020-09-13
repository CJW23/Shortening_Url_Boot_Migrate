package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.respository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService {
    private final UrlRepository urlRepository;
    private final EntityManager em;
    private final UrlManager urlManager;

    @Override
    public String findOriginalUrl(Long id) {
        return findUrl(id).getOriginalUrl();
    }

    @Override
    public Url findUrl(Long id) {
        return urlRepository.findUrl(id);
    }

    @Override
    @Transactional
    public void saveUrlAccess(AccessUrl accessUrl) {
        urlRepository.saveUrlAccess(accessUrl);
    }

    /**
     * 단축 URL 생성 로직
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public Url makeUrl(Url url) throws Exception {
        //URL 정규식 검증 -> 정규식 맞지 않으면 앞에 http://를 단다.
        if (!urlManager.checkUrlRegex(url.getOriginalUrl())) {
            url.setOriginalUrl("http://" + url.getOriginalUrl());
        }
        //URL 존재여부
        if (!urlManager.checkExistUrl(url.getOriginalUrl())) {
            throw new UrlException("존재하지 않는 URL");
        }
        //추후 금지URL, 이미 등록URL 예외처리

        //URL PATH 붙이기
        int randomId = urlManager.makeRandom();
        url.setShortUrl("http://localhost:8080/" + Base62.encode(randomId));
        url.setId((long) randomId);

        return url;
    }

    @Override
    @Transactional
    public Map<String, String> saveUrl(Url url) throws Exception {
        Map<String, String> map = new HashMap<>();
        url = makeUrl(url);
        urlRepository.save(url);
        map.put("originalUrl", url.getOriginalUrl());
        map.put("shortUrl", url.getShortUrl());
        return map;
    }
}

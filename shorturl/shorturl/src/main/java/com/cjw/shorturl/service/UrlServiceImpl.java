package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.CreateUserUrlDTO;
import com.cjw.shorturl.dto.UrlDetailResponse;
import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.exception.MakeRandomException;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.lib.Base62;
import com.cjw.shorturl.respository.UrlRepositoryImpl;
import com.cjw.shorturl.service.manager.UrlManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl {
    private final UrlRepositoryImpl urlRepository;
    private final UrlManager urlManager;
    private final EntityManager em;

    /**
     * 특정 URL Entity의 원본 URL 반환
     *
     * @param id
     * @return
     */
    public String findOriginalUrl(Long id) {
        return findUrl(id).getOriginalUrl();
    }

    /**
     * Url Entity
     *
     * @param id
     * @return
     */
    public Url findUrl(Long id) {
        return urlRepository.findUrl(id);
    }

    /**
     * UrlAccess 저장
     *
     * @param accessUrl
     */
    @Transactional
    public void saveUrlAccess(AccessUrl accessUrl) {
        urlRepository.saveUrlAccess(accessUrl);
    }

    /**
     * 단축 URL Entity 생성
     *
     * @param url Entity
     * @return url Entity
     * @throws Exception
     */
    private Url makeUrl(Url url) throws Exception {
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
        url.setShortUrl("http://localhost:8080/a/" + Base62.encode(randomId));
        url.setId((long) randomId);
        return url;
    }

    private Url makeUserUrl(Long id, CreateUserUrlDTO userUrl) throws MakeRandomException, UrlException {
        Url url = new Url();
        if (!urlManager.checkUrlRegex(userUrl.getUrl())) {
            url.setOriginalUrl("http://" + userUrl.getUrl());
        } else {
            url.setOriginalUrl(userUrl.getUrl());
        }
        if (!urlManager.checkExistUrl(userUrl.getUrl())) {
            throw new UrlException("존재하지 않는 URL");
        }
        if (!urlManager.checkAlreadyUrl(id, url.getOriginalUrl())) {
            throw new UrlException("이미 존재하는 URL");
        }
        int randomId = urlManager.makeRandom();
        url.setShortUrl("http://localhost:8080/a/" + Base62.encode(randomId));
        url.setId((long) randomId);
        return url;
    }

    /**
     * URL 등록
     *
     * @param url
     * @return
     * @throws Exception
     */
    @Transactional
    public Map<String, String> saveUrl(Url url) throws Exception {
        Map<String, String> map = new HashMap<>();
        url = makeUrl(url);
        urlRepository.save(url);
        map.put("originalUrl", url.getOriginalUrl());
        map.put("shortUrl", url.getShortUrl());
        return map;
    }

    @Transactional
    public void saveUserUrl(Long id, CreateUserUrlDTO userUrl) throws MakeRandomException, UrlException {
        Url url = makeUserUrl(id, userUrl);
        url.setUser(em.find(User.class, id));
        urlRepository.save(url);
    }

    public UrlDetailResponse findUrlDetail(Long userId, Long urlId) {
        Url detail= urlRepository.findUrlDetail(userId, urlId);
        return Url.makeDetailUrl(detail);
    }
}

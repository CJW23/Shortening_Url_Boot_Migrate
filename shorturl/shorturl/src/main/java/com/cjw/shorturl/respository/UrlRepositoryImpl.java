package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UrlRepositoryImpl implements UrlRepository{
    private final EntityManager em;

    @Override
    public Url findUrl(Long urlId) {
        return em.find(Url.class, urlId);
    }

    @Override
    public void save(Url url) {
        em.persist(url);
    }

    @Override
    public Url selectOriginalUrl(Long urlId) {
        return null;
    }

    @Override
    public void saveUrlAccess(AccessUrl accessUrl) {
        em.persist(accessUrl);
    }

    @Override
    public boolean getBanUrl(String url) {
        return false;
    }
}

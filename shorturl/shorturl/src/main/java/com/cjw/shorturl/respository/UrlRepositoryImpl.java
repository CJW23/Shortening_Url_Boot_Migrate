package com.cjw.shorturl.respository;

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
    public Url findUrlOne(Long urlId) {
        return em.find(Url.class, urlId);
    }

    @Override
    public void saveUrl(Url url) {
        em.persist(url);
    }

    @Override
    public Url selectOriginalUrl(Long urlId) {
        return null;
    }

    @Override
    public List<Url> registerUrlAccess(Long urlId, String link) {
        return null;
    }

    @Override
    public boolean getBanUrl(String url) {
        return false;
    }
}

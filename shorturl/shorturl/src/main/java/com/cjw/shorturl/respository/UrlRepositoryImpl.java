package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UrlRepositoryImpl implements UrlRepository{
    private final EntityManager em;

    /*@Override
    public boolean checkExistUrlId(int urlId) {
        return false;
    }*/
    @Override
    public Url findOne(int urlId) {
        return em.find(Url.class, urlId);
    }

    @Override
    public void registerUrl(Url url) {
        em.persist(url);
    }

    @Override
    public Url selectOriginalUrl(int urlId) {
        return null;
    }

    @Override
    public List<Url> registerUrlAccess(int urlId, String link) {
        return null;
    }

    @Override
    public boolean getBanUrl(String url) {
        return false;
    }
}

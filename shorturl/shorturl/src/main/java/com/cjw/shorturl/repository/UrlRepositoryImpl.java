package com.cjw.shorturl.repository;

import com.cjw.shorturl.entity.AccessUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UrlRepositoryImpl {
    private final EntityManager em;

    public Url findUrl(Long urlId) {
        return em.find(Url.class, urlId);
    }

    public void save(Url url) {
        em.persist(url);
    }

    public Url selectOriginalUrl(Long urlId) {
        return null;
    }

    public void saveUrlAccess(AccessUrl accessUrl) {
        em.persist(accessUrl);
    }

    public boolean getBanUrl(String url) {
        return false;
    }

    public boolean findAlreadyUrl(Long id, String url) {
        TypedQuery<Url> query = em.createQuery(
                "select m from Url as m where m.originalUrl = ?1 and m.user = ?2",
                Url.class).setParameter(1, url).setParameter(2, em.find(User.class, id));
        return query.getResultList().size() < 1;
    }

    public Url findUrlDetail(Long userId, Long urlId) {
        TypedQuery<Url> query = em.createQuery(
                "select m from Url as m where m.id = ?1 and m.user = ?2",
                Url.class).setParameter(1, urlId).setParameter(2, em.find(User.class, userId));
        return query.getSingleResult();
    }
}

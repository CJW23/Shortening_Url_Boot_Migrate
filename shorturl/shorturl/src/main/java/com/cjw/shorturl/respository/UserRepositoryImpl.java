package com.cjw.shorturl.respository;

import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl {
    private final EntityManager em;

    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    public User findUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery("select m from User as m where m.email = ?1", User.class)
                .setParameter(1, email);
        return query.getSingleResult();
    }

    public Long saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(user);
        return user.getId();
    }

    public boolean findExistNickname(String nickname) {
        TypedQuery<User> query = em.createQuery("select m from User as m where m.nickname = ?1", User.class)
                .setParameter(1, nickname);
        return query.getResultList().size() < 1;
    }

    public void removeUser(User user) {
        em.remove(user);
    }

    public List<Object[]> findTotalUrlAccessById(Long id) {
        Query query =
                em.createNativeQuery(
                        "SELECT date_format(access_time, '%m-%d') AS dates, SUM(1) AS count " +
                                "FROM access_url, user, url " +
                                "WHERE user.user_id = ?1 AND " +
                                "user.user_id = url.user_id AND " +
                                "url.url_id = access_url.url_id AND " +
                                "date_format(access_time, '%Y-%m-%d') BETWEEN (NOW() - INTERVAL 7 DAY) AND NOW() " +
                                "GROUP BY dates " +
                                "ORDER BY dates").setParameter(1, id);
        return (List<Object[]>) query.getResultList();
    }

    public List<Object[]> findUrlAccessById(Long id) {
        Query query =
                em.createNativeQuery(
                        "SELECT date_format(access_time, '%m-%d') AS dates, SUM(1) AS count " +
                                "FROM access_url " +
                                "WHERE access_url.url_id = ?1 AND " +
                                "date_format(access_time, '%Y-%m-%d') between (NOW() - INTERVAL 7 DAY) AND NOW() " +
                                "GROUP BY dates " +
                                "ORDER BY dates;").setParameter(1, id);
        return (List<Object[]>) query.getResultList();
    }

    public List<Object[]> findUrlLinkById(Long id) {
		Query query =
                em.createNativeQuery(
                        "SELECT IFNULL(before_url, 'Direct') AS beforeUrl, COUNT(1) AS cnt " +
                                "FROM access_url " +
                                "WHERE url_id = ?1 " +
                                "GROUP BY before_url").setParameter(1, id);
		return (List<Object[]>) query.getResultList();
    }
}

package com.cjw.shorturl.respository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepository {
    private final EntityManager em;

    /**
     * 등록된 URL의 총 개수
     */
    public int findTotalUrlCount() {
        Query query =
                em.createNativeQuery(
                        "SELECT count(1) AS urlCount FROM url");
        return ((List<Object[]>) query.getResultList()).size();
    }

    /**
     * 등록된 유저 총 수
     */
    public int findTotalUserCount() {
        Query query =
                em.createNativeQuery(
                        "SELECT count(1) AS userCount FROM user");
        return ((List<Object[]>) query.getResultList()).size();
    }

    /**
     * URL Access 기록 총 개수
     */
    public int findTotalAccessUrlCount() {
        Query query =
                em.createNativeQuery(
                        "SELET count(1) AS accessCount FROM access_url");
        return ((List<Object[]>) query.getResultList()).size();
    }

    /**
     * 7일간 일별 생성된 회원 총 개수
     */
    public List<Object[]> findDayUserCount() {
        Query query =
                em.createNativeQuery(
                        "SELECT date_format(created_at, '%m-%d') AS dates, COUNT(1) AS count " +
                                "FROM user " +
                                "WHERE date_format(created_at, '%Y-%m-%d') BETWEEN (NOW() - INTERVAL 7 DAY) AND NOW() " +
                                "GROUP BY dates " +
                                "ORDER BY dates");
        return ((List<Object[]>) query.getResultList());
    }

    /**
     * 7일간 일별 생성된 URL 총 개수
     */
    public List<Object[]> findDayUrlCount() {
        Query query =
                em.createNativeQuery(
                        "SELECT date_format(created_at, '%m-%d') AS dates, COUNT(1) AS count " +
                                "FROM url " +
                                "WHERE date_format(created_at, '%Y-%m-%d') BETWEEN (NOW() - INTERVAL 7 DAY) AND NOW() " +
                                "GROUP BY dates " +
                                "ORDER BY dates");
        return ((List<Object[]>) query.getResultList());
    }

    /**
     * 7일간 일별 생성된 접근 URL 총 개수
     */
    public List<Object[]> findDayAccessUrlCount() {
        Query query =
                em.createNativeQuery(
                        "SELECT date_format(access_time, '%m-%d') AS dates, COUNT(1) AS count " +
                                "FROM access_url " +
                                "WHERE date_format(access_time, '%Y-%m-%d') BETWEEN (NOW() - INTERVAL 7 DAY) AND NOW() " +
                                "GROUP BY dates " +
                                "ORDER BY dates");
        return ((List<Object[]>) query.getResultList());
    }
}

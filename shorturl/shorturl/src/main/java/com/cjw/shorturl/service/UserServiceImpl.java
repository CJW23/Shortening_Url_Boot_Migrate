package com.cjw.shorturl.service;

import javax.persistence.EntityManager;

import com.cjw.shorturl.dto.*;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {
    private final UserRepositoryImpl userRepository;
    private final EntityManager em;

    @Transactional
    public Long join(User user) {
        return userRepository.saveUser(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserMainPageDto findUserMainDataById(Long id) {
        List<DayChartDto> totalUrlAccessList = new ArrayList<>();
        for (Object[] a : userRepository.findTotalUrlAccessById(id)) {
            DayChartDto tmp = new DayChartDto((String) a[0], ((BigDecimal) a[1]).intValue());
            totalUrlAccessList.add(tmp);
        }
        return UserMainPageDto.makeUserMainPage(em.find(User.class, id), totalUrlAccessList);
    }

    @Transactional
    public void removeUrlById(List<Long> urlList){
        for(Long urlId : urlList){
            Url url = em.find(Url.class, urlId);
            em.remove(url);
        }
    }

    public UserTotalDataDto findTotalUrlData(Long userId) {
        User user = em.find(User.class, userId);
        return new UserTotalDataDto(user.getUrls().size(), user.totalAccessUrlCount());
    }

    public List<UserMainUrlDto> findUrlListByUserId(Long userId) {
        return UserMainUrlDto.makeUserUrlList(em.find(User.class, userId).getUrls());
    }

    public List<DayChartDto> findUrlAccessData(Long urlId) {
        List<DayChartDto> urlAccessList = new ArrayList<>();
        for (Object[] a : userRepository.findUrlAccessById(urlId)) {
            DayChartDto tmp = new DayChartDto((String) a[0], ((BigDecimal) a[1]).intValue());
            urlAccessList.add(tmp);
        }
        return urlAccessList;
    }

    public List<LinkChartDto> findUrlLinkData(Long urlId) {
        List<LinkChartDto> urlLinkList = new ArrayList<>();
        for (Object[] a : userRepository.findUrlLinkById(urlId)) {
            LinkChartDto tmp = new LinkChartDto((String) a[0], ((BigInteger)a[1]).intValue());
            urlLinkList.add(tmp);
        }
        return urlLinkList;
    }
}

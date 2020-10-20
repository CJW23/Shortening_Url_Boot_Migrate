package com.cjw.shorturl.service;

import javax.persistence.EntityManager;

import com.cjw.shorturl.dto.*;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

    public UserMainPageDTO findUserMainDataById(Long id) {
        List<DayChartDTO> totalUrlAccessList = new ArrayList<>();
        for (Object[] a : userRepository.findTotalUrlAccessById(id)) {
            DayChartDTO tmp = new DayChartDTO((String) a[0], ((BigDecimal) a[1]).intValue());
            totalUrlAccessList.add(tmp);
        }
        return UserMainPageDTO.makeUserMainPage(em.find(User.class, id), totalUrlAccessList);
    }

    @Transactional
    public void removeUrlById(List<Long> urlList){
        for(Long urlId : urlList){
            Url url = em.find(Url.class, urlId);
            em.remove(url);
        }
    }

    public UserTotalDataDTO findTotalUrlData(Long userId) {
        User user = em.find(User.class, userId);
        return new UserTotalDataDTO(user.getUrls().size(), user.totalAccessUrlCount());
    }

    public List<UserMainUrlDTO> findUrlListByUserId(Long userId) {
        return UserMainUrlDTO.makeUserUrlList(em.find(User.class, userId).getUrls());
    }

    public List<DayChartDTO> findUrlAccessData(Long urlId) {
        List<DayChartDTO> urlAccessList = new ArrayList<>();
        for (Object[] a : userRepository.findUrlAccessById(urlId)) {
            DayChartDTO tmp = new DayChartDTO((String) a[0], ((BigDecimal) a[1]).intValue());
            urlAccessList.add(tmp);
        }
        return urlAccessList;
    }

    public List<LinkChartDTO> findUrlLinkData(Long urlId) {
        List<LinkChartDTO> urlLinkList = new ArrayList<>();
        for (Object[] a : userRepository.findUrlLinkById(urlId)) {
            LinkChartDTO tmp = new LinkChartDTO((String) a[0], ((BigInteger)a[1]).intValue());
            urlLinkList.add(tmp);
        }
        return urlLinkList;
    }
}

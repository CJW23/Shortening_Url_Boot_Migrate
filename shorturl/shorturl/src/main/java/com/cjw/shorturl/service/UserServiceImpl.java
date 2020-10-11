package com.cjw.shorturl.service;

import javax.persistence.EntityManager;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UserMainPageDTO;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        for(Object[] a : userRepository.findTotalUrlAccessById(id)){
            DayChartDTO tmp = new DayChartDTO((String)a[0], ((BigDecimal)a[1]).intValue());
            totalUrlAccessList.add(tmp);
        }
        return UserMainPageDTO.makeUserMainPage(em.find(User.class, id), totalUrlAccessList);
    }
}

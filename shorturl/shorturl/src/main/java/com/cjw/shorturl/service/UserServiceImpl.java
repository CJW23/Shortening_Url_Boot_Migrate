package com.cjw.shorturl.service;

import javax.persistence.EntityManager;

import com.cjw.shorturl.dto.UserMainPageDTO;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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

        return UserMainPageDTO.makeUserMainPage(em.find(User.class, id));
    }
}

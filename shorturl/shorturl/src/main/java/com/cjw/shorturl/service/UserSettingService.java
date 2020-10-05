package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSettingService {
    private final UserRepositoryImpl userRepository;
    private final EntityManager em;

    @Transactional
    public void updateUserName(Long id, String name) {
        User user = userRepository.findUserById(id);
        user.setName(name);
    }
}

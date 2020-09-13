package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.LoginDTO;
import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl {
    private final EntityManager em;

    public Long join(User user) {
        em.persist(user);
        return user.getId();
    }

    public void login(LoginDTO info) {
        
    }
}

package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.LoginDTO;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.respository.LoginRepositoryImpl;
import com.cjw.shorturl.respository.UserRepositoryImpl;
import com.cjw.shorturl.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements UserDetailsService {
    private final LoginRepositoryImpl loginRepository;

    @Transactional
    public Long join(User user) {
        return loginRepository.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginRepository.findUserByEmail(username);
        log.info(user.getEmail());
        log.info(user.getPassword());
        return new MyUserDetails(user);
    }
}

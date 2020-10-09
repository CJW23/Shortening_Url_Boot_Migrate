package com.cjw.shorturl.service;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.exception.SamePasswordException;
import com.cjw.shorturl.exception.WrongCurrentPasswordException;
import com.cjw.shorturl.respository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSettingService {
    private final UserRepositoryImpl userRepository;
    private final EntityManager em;

    @Transactional
    public void updateUserName(Long id, String name) {
        User user = userRepository.findUserById(id);
        user.setName(name);
    }

    public boolean checkNickname(String nickname) {
        return userRepository.findExistNickname(nickname);
    }

    @Transactional
    public void updateUserNickname(Long id, String nickname) {
        User user = userRepository.findUserById(id);
        user.setNickname(nickname);
    }

    @Transactional
    public void changePassword(Long id, String curPassword, String newPassword) throws WrongCurrentPasswordException, SamePasswordException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserById(id);
        if (!passwordEncoder.matches(curPassword, user.getPassword())) {
            throw new WrongCurrentPasswordException("기존 비밀번호가 틀립니다");
        } else if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("기존 비밀번호와 같습니다");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public void deleteUser(Long id, String curPassword) throws WrongCurrentPasswordException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserById(id);
        log.info("awd " + user.getPassword());
        if (!passwordEncoder.matches(curPassword, user.getPassword())) {
            throw new WrongCurrentPasswordException("기존 비밀번호가 틀립니다");
        }
        userRepository.removeUser(user);
    }
}

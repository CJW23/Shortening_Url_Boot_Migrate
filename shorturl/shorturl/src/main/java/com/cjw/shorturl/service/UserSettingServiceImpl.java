package com.cjw.shorturl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSettingServiceImpl implements UserSettingService{
    @Override
    public void changeUserInfo(int userId, String name) {

    }

    @Override
    public void changeUserNickname(int userId, String nickname) {

    }

    @Override
    public void changeUserPassword(int userId, String password) {

    }

    @Override
    public void dropUser(int userId) {

    }

    @Override
    public boolean checkUserNickname(String nickname) {
        return false;
    }
}

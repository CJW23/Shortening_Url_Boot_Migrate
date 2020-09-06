package com.cjw.shorturl.service;

public interface UserSettingService {
    void changeUserInfo(int userId, String name);
    void changeUserNickname(int userId, String nickname);
    void changeUserPassword(int userId, String password);
    void dropUser(int userId);
    boolean checkUserNickname(String nickname);
}

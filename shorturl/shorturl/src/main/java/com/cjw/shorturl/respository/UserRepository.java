package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;

import java.util.List;

public interface UserRepository {
    List<Url> selectUserUrlList(int userId);
    boolean selectUserUrl(String Url);
    UrlCountDTO selectUserTotalData(int userId);
    Url selectUserUrlDetail(int urlId);
    List<DayChartDTO> selectTotalUrlAccessData(int userId);
    List<DayChartDTO> selectIndividualUrlAccessData(int urlId);
    void updateUserInfo(int userId, String name);
    void updateUserNickname(int userId, String nickname);
    void updatePassword(int userId, String password);
    void deleteUser(int userId);
    void deleteUrl(int userId, List<Integer> urlIdList);
    List<DayChartDTO> selectLinkAccessData(int urlId);
    boolean checkUserNickname(String nickname);
}

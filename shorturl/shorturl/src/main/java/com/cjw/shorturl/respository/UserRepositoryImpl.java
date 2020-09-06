package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<Url> selectUserUrlList(int userId) {
        return null;
    }

    @Override
    public boolean selectUserUrl(String Url) {
        return false;
    }

    @Override
    public UrlCountDTO selectUserTotalData(int userId) {
        return null;
    }

    @Override
    public Url selectUserUrlDetail(int urlId) {
        return null;
    }

    @Override
    public List<DayChartDTO> selectTotalUrlAccessData(int userId) {
        return null;
    }

    @Override
    public List<DayChartDTO> selectIndividualUrlAccessData(int urlId) {
        return null;
    }

    @Override
    public void updateUserInfo(int userId, String name) {

    }

    @Override
    public void updateUserNickname(int userId, String nickname) {

    }

    @Override
    public void updatePassword(int userId, String password) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void deleteUrl(int userId, List<Integer> urlIdList) {

    }

    @Override
    public List<DayChartDTO> selectLinkAccessData(int urlId) {
        return null;
    }

    @Override
    public boolean checkUserNickname(String nickname) {
        return false;
    }
}

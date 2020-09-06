package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.entity.BanUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository{
    @Override
    public int selectAdminTotalUrlCount() {
        return 0;
    }

    @Override
    public int selectAdminTotalUserCount() {
        return 0;
    }

    @Override
    public int selectAdminTotalAccessUrlCount() {
        return 0;
    }

    @Override
    public List<DayChartDTO> selectAdminDayUrlCount() {
        return null;
    }

    @Override
    public List<DayChartDTO> selectAdminDayUserCount() {
        return null;
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public void giveAuth(int userId) {

    }

    @Override
    public void withdrawAuth(int userId) {

    }

    @Override
    public void deleteUrl(int urlId) {

    }

    @Override
    public void insertAdminBanUrl(String url) {

    }

    @Override
    public boolean selectAdminUrl(String url) {
        return false;
    }

    @Override
    public List<BanUrl> selectAdminBanUrls(String search) {
        return null;
    }

    @Override
    public void deleteBanUrl(int banUrlId) {

    }

    @Override
    public List<DayChartDTO> selectAdminDayAccessUrlCount() {
        return null;
    }
}

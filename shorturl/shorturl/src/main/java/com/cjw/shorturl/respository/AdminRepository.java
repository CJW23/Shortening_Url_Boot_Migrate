package com.cjw.shorturl.respository;

import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.entity.BanUrl;

import java.util.List;

public interface AdminRepository {
    int selectAdminTotalUrlCount();
    int selectAdminTotalUserCount();
    int selectAdminTotalAccessUrlCount();
    List<DayChartDTO> selectAdminDayUrlCount();
    List<DayChartDTO> selectAdminDayUserCount();
    void deleteUser(int userId);
    void giveAuth(int userId);
    void withdrawAuth(int userId);
    //void selectAdminUrls() 검색
    void deleteUrl(int urlId);
    void insertAdminBanUrl(String url);
    boolean selectAdminUrl(String url);
    List<BanUrl> selectAdminBanUrls(String search);
    void deleteBanUrl(int banUrlId);
    //void selectAdminUsers() 검색
    List<DayChartDTO> selectAdminDayAccessUrlCount();
}

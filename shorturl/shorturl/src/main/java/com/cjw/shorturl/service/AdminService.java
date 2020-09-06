package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.DayChartDTO;

import java.util.List;

public interface AdminService {
    public int adminTotalUrlCount();
    public int adminTotalUserCount();
    public int adminTotalUrlAccessCount();
    public List<DayChartDTO> adminDayUrlCount();
    public List<DayChartDTO> adminDayUserCount();
    public int adminRemoveUser(int userId);
    //public List<User> adminFindUsers()    검색 리스트 만들기
    public int adminGiveAuth(int userId);
    public int adminWithdrawAuth(int userId);
    //public List<Url> adminFindUrls() 검색 리스트 만들기
    public int adminRemoveUrl(int urlId);
    public int adminSaveBanUrl(String url);
    //public List<BanUrl> adminFindBanUrls(); //검색 리스트
    public int adminRemoveBanUrl(int banUrlId);
    public List<DayChartDTO> adminDayAccessUrlCount();
}

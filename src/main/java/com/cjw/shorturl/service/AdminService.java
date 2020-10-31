package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.AdminMainPageDto;
import com.cjw.shorturl.dto.DayChartDto;
import com.cjw.shorturl.dto.UrlSearchDto;
import com.cjw.shorturl.dto.SearchDto;
import com.cjw.shorturl.entity.BanUrl;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.repository.AdminRepository;
import com.cjw.shorturl.repository.AdminSearchRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminSearchRepository adminSearchRepository;

    public AdminMainPageDto getAdminData() {
        List<DayChartDto> dayUserList = new ArrayList<>();
        List<DayChartDto> dayUrlList = new ArrayList<>();
        List<DayChartDto> dayAccessList = new ArrayList<>();

        for (Object[] d : adminRepository.findDayUserCount()) {
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger) d[1]).intValue());
            dayUserList.add(tmp);
        }
        for (Object[] d : adminRepository.findDayUrlCount()) {
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger) d[1]).intValue());
            dayUrlList.add(tmp);
        }
        for (Object[] d : adminRepository.findDayAccessUrlCount()) {
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger) d[1]).intValue());
            dayAccessList.add(tmp);
        }
        return AdminMainPageDto.makeData(
                adminRepository.findTotalUrlCount(),
                adminRepository.findTotalUserCount(),
                adminRepository.findTotalAccessUrlCount(),
                dayUserList,
                dayUrlList,
                dayAccessList);
    }

    public Page<User> getUserList(int pageNo, SearchDto userSearch) throws Exception {
        PageHelper.startPage(pageNo, 10);
        return adminSearchRepository.findSearchUser(userSearch);
    }

    public Page<Url> getUrlList(int pageNo, SearchDto urlSearch) throws Exception {
        PageHelper.startPage(pageNo, 10);
        return adminSearchRepository.findSearchUrl(urlSearch);
    }

    public Page<BanUrl> getBanUrlList(int pageNo) throws Exception {
        PageHelper.startPage(pageNo, 10);
        return adminSearchRepository.findSearchBanUrl();
    }

    @Transactional
    public void deleteUser(Long id) {
        adminRepository.deleteUserById(id);
    }

    @Transactional
    public void giveAuth(Long id) {
        adminRepository.giveAdminAuthById(id);
    }

    @Transactional
    public void withdrawAuth(Long id) {
        adminRepository.withdrawAdminAuthById(id);
    }

    @Transactional
    public void deleteUrl(Long id) {
        adminRepository.deleteUrlById(id);
    }

    @Transactional
    public void deleteBanUrl(Long id) {
        adminRepository.deleteBanUrlById(id);
    }
}

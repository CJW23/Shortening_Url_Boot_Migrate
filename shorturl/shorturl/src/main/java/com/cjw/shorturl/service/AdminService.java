package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.AdminMainPageDto;
import com.cjw.shorturl.dto.DayChartDto;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.repository.AdminRepository;
import com.cjw.shorturl.repository.AdminMapper;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminSearchRepository;

    public AdminMainPageDto getAdminData() {
        List<DayChartDto> dayUserList = new ArrayList<>();
        List<DayChartDto> dayUrlList = new ArrayList<>();
        List<DayChartDto> dayAccessList = new ArrayList<>();

        for (Object[] d : adminRepository.findDayUserCount()) {
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger)d[1]).intValue());
            dayUserList.add(tmp);
        }
        for (Object[] d : adminRepository.findDayUrlCount()) {
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger)d[1]).intValue());
            dayUrlList.add(tmp);
        }
        for(Object[] d : adminRepository.findDayAccessUrlCount()){
            DayChartDto tmp = new DayChartDto((String) d[0], ((BigInteger)d[1]).intValue());
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

    public List<User> getUserList() throws Exception {
        return adminSearchRepository.findUserBySearch();
    }
}

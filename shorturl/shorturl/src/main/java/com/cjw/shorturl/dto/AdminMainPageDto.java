package com.cjw.shorturl.dto;

import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

@Data
public class AdminMainPageDto {
    private int totalUrlCount;
    private int totalUserCount;
    private int totalAccessUrlCount;
    private String dayUserCount;
    private String dayUrlCount;
    private String dayAccessUrlCount;

    public static AdminMainPageDto makeData(int totalUrlCount,
                           int totalUserCount,
                           int totalAccessUrlCount,
                           List<DayChartDto> dayUserCount,
                           List<DayChartDto> dayUrlCount,
                           List<DayChartDto> dayAccessUrlCount) {
        AdminMainPageDto data = new AdminMainPageDto();
        data.setTotalUrlCount(totalUrlCount);
        data.setTotalUserCount(totalUserCount);
        data.setTotalAccessUrlCount(totalAccessUrlCount);
        data.setDayUserCount(new Gson().toJson(dayUserCount));
        data.setDayUrlCount(new Gson().toJson(dayUrlCount));
        data.setDayAccessUrlCount(new Gson().toJson(dayAccessUrlCount));
        return data;
    }
}

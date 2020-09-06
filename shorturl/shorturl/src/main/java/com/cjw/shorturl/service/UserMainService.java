package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.CreateUserUrlDTO;
import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;

import java.util.List;

public interface UserMainService {
    List<Url> getUserUrlList(int userId);
    Url getUserUrlDetail(int userId);
    UrlCountDTO getUserTotalData(int userId);
    List<Url> makeUserUrl(CreateUserUrlDTO info);
    List<DayChartDTO> getTotalUrlAccessData(int userId);
    List<Url> removeUserUrl(List<Integer> urlIdList);
    List<DayChartDTO> getIndividualUrlAccessData(int urlId);
    List<DayChartDTO> selectLinkAccessData(int urlId);
}

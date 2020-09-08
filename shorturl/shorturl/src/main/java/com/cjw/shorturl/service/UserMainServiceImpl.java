package com.cjw.shorturl.service;

import com.cjw.shorturl.dto.CreateUserUrlDTO;
import com.cjw.shorturl.dto.DayChartDTO;
import com.cjw.shorturl.dto.UrlCountDTO;
import com.cjw.shorturl.entity.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMainServiceImpl implements UserMainService{

    @Override
    public List<Url> getUserUrlList(int userId) {
        return null;
    }

    @Override
    public Url getUserUrlDetail(int userId) {
        return null;
    }

    @Override
    public UrlCountDTO getUserTotalData(int userId) {
        return null;
    }

    @Override
    public List<Url> makeUserUrl(CreateUserUrlDTO info) {
        return null;
    }

    @Override
    public List<DayChartDTO> getTotalUrlAccessData(int userId) {
        return null;
    }

    @Override
    public List<Url> removeUserUrl(List<Integer> urlIdList) {
        return null;
    }

    @Override
    public List<DayChartDTO> getIndividualUrlAccessData(int urlId) {
        return null;
    }

    @Override
    public List<DayChartDTO> selectLinkAccessData(int urlId) {
        return null;
    }
}

package com.cjw.shorturl.dto;

import com.cjw.shorturl.entity.Url;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저 메인 페이지 URL List 담을 DTO
 */
@Data
@AllArgsConstructor
public class UserMainUrlDTO {
    private Long id;
    private String shortUrl;
    private Long userId;
    private String nameUrl;
    private long count;

    public static List<UserMainUrlDTO> makeUserUrlList(List<Url> list) {
        List<UserMainUrlDTO> urlList = new ArrayList<>();
        for(Url url : list){
            UserMainUrlDTO urlDTO = new UserMainUrlDTO(url.getId(), url.getShortUrl(), url.getUser().getId(), url.getNameUrl(), url.getCount());
            if(urlDTO.getNameUrl().equals("")){
                urlDTO.setNameUrl(url.getOriginalUrl());
            }
            urlList.add(urlDTO);
        }
        return urlList;
    }
}

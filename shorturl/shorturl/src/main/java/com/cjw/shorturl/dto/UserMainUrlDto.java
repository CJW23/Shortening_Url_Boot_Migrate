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
public class UserMainUrlDto {
    private Long id;
    private String shortUrl;
    private Long userId;
    private String nameUrl;
    private long count;

    public static List<UserMainUrlDto> makeUserUrlList(List<Url> list) {
        List<UserMainUrlDto> urlList = new ArrayList<>();
        for(Url url : list){
            UserMainUrlDto urlDTO = new UserMainUrlDto(url.getId(), url.getShortUrl(), url.getUser().getId(), url.getNameUrl(), url.getCount());
            if(urlDTO.getNameUrl().equals("")){
                urlDTO.setNameUrl(url.getOriginalUrl());
            }
            urlList.add(urlDTO);
        }
        return urlList;
    }
}

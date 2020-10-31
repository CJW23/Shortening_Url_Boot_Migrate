package com.cjw.shorturl.dto;

import com.cjw.shorturl.entity.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UrlDetailResponse {
    private Long id;
    private String createdAt;
    private String nameUrl;
    private String originalUrl;
    private String shortUrl;
    private long count;

    public static UrlDetailResponse makeDetailUrl(Url url) {
        UrlDetailResponse response = new UrlDetailResponse();
        response.setId(url.getId());
        response.setCount(url.getCount());
        response.setCreatedAt(url.getCreatedAt().toString());
        if (url.getNameUrl() == null) {
            response.setNameUrl(url.getOriginalUrl());
        } else {
            response.setNameUrl(url.getNameUrl());
        }
        response.setOriginalUrl(url.getOriginalUrl());
        response.setShortUrl(url.getShortUrl());
        return response;
    }
}

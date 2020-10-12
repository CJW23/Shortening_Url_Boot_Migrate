package com.cjw.shorturl.dto;

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
    private Long count;
}

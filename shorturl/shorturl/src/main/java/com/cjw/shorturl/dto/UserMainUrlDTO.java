package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}

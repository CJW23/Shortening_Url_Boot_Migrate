package com.cjw.shorturl.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class UrlCountDto {
    int UrlCount;
    int UrlAccessCount;
}

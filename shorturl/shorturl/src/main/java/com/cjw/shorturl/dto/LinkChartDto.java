package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class LinkChartDto {
    private String beforeUrl;
    private int cnt;
}

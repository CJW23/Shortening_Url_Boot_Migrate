package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class DayChartDTO {
    private String dates;
    private int count;
}

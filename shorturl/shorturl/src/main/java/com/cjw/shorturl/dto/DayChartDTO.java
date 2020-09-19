package com.cjw.shorturl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DayChartDTO {
    private Date day;
    private int count;
}

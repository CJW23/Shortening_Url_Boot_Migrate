package com.cjw.shorturl.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DayChartDTO {
    private Date day;
    private int count;
}

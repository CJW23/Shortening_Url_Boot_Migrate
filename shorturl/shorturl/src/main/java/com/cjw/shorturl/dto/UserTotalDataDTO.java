package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTotalDataDTO {
    private long totalNum;
    private long totalSum;
}

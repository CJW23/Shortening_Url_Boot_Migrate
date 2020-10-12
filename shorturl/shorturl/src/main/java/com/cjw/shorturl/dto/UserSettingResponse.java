package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserSettingResponse {
    private final String rst;
    private final Integer type;

    public UserSettingResponse(String rst){
        this.rst = rst;
        this.type = null;
    }
}

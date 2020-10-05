package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserSettingResponseDTO {
    private final String rst;
    private final String type;

    public UserSettingResponseDTO(String rst){
        this.rst = rst;
        this.type = "";
    }
}

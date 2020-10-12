package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@AllArgsConstructor
public class CreateUrlResponse {
    private String rst;
    private String msg;

    public CreateUrlResponse(String rst) {
        this.rst = rst;
        this.msg = null;
    }
}

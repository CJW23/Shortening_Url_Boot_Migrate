package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@AllArgsConstructor
public class CreateUrlResponseDTO {
    private String rst;
    private String msg;

    public CreateUrlResponseDTO(String rst) {
        this.rst = rst;
        this.msg = null;
    }
}

package com.cjw.shorturl.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class LoginDto {
    private String email;
    private String password;
}

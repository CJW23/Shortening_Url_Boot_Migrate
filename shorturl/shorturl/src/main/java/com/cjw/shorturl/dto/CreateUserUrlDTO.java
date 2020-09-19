package com.cjw.shorturl.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CreateUserUrlDTO {
    int userId;
    String url;
    String nameUrl;
}

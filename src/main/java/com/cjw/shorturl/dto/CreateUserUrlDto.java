package com.cjw.shorturl.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CreateUserUrlDto {
    String url;
    String nameUrl;
}

package com.cjw.shorturl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserUrlDTO {
    int userId;
    String url;
    String nameUrl;
}

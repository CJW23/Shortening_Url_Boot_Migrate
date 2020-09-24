package com.cjw.shorturl.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class SignUpDTO {
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phone;
}

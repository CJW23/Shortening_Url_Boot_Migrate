package com.cjw.shorturl.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class SignUpDto {
	@NotBlank(message = "이메일 공백")
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phone;
}

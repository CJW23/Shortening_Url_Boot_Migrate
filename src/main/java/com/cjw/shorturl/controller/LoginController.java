package com.cjw.shorturl.controller;

import javax.validation.Valid;

import com.cjw.shorturl.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjw.shorturl.dto.SignUpDto;
import com.cjw.shorturl.entity.User;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final UserServiceImpl userService;

	@PostMapping("/auth/signUp")
	public String processSignUp(@Valid SignUpDto signUpDTO, BindingResult bindingResult) {
		//예외처리 aop 구현 해야함.
		if(bindingResult.hasErrors()){
			return "auth/signup";
		}
		User user = User.makeSignUpUser(signUpDTO);
		userService.join(user);
		return "redirect:/user/main";
	}
}

package com.cjw.shorturl.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjw.shorturl.dto.SignUpDTO;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginServiceImpl loginService;

	@PostMapping("/auth/signUp")
	public String processSignUp(@Valid SignUpDTO signUpDTO, BindingResult bindingResult) {
		//예외처리 aop 구현 해야함.
		if(bindingResult.hasErrors()){
			return "auth/signup";
		}
		User user = User.makeSignUpUser(signUpDTO);
		loginService.join(user);
		return "redirect:/user/main";
	}
}

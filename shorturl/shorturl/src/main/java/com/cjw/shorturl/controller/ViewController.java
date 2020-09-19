package com.cjw.shorturl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {
	private final LoginServiceImpl loginService;

	@GetMapping("/user/home")
	public String main() {
		return "home";
	}

	@GetMapping("/user/signUp")
	public String signUp() {
		return "auth/signup";
	}

	@PostMapping("/user/signUp")
	public String processSignUp(SignUpDTO signUpDTO) {
		//예외처리 aop 구현 해야함.
		User user = User.makeSignUpUser(signUpDTO);
		loginService.join(user);
		return "redirect:/user/home";
	}

	@GetMapping("/user/denied")
	public String denied() {
		return "auth/access_denied";
	}
}

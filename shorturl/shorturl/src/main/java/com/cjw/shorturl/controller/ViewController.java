package com.cjw.shorturl.controller;

import com.cjw.shorturl.dto.SignUpDTO;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/auth/signUp")
    public String signUp() {
        return "auth/signup";
    }

    @PostMapping("/auth/signUp")
    public String processSignUp(SignUpDTO signUpDTO) {
        //예외처리 aop 구현 해야함.
        User user = User.makeSignUpUser(signUpDTO);
        loginService.join(user);
        return "redirect:/user/main";
    }

    @GetMapping("/auth/denied")
    public String denied() {
        return "auth/access_denied";
    }

    @GetMapping("/guest/main")
    public String guestMain() {
        return "home";
    }

    @GetMapping("/user/main")
    public String userMain() {
        return "user/main";
    }

    @GetMapping("/admin/main")
    public String adminMain() {
        return "admin/main";
    }
}

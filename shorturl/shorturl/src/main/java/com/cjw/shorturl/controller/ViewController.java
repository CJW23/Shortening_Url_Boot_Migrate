package com.cjw.shorturl.controller;

import com.cjw.shorturl.dto.SignUpDto;

import com.cjw.shorturl.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cjw.shorturl.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {
    private final UserServiceImpl userService;

    /**
     * 회원가입 페이지
     */
    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("signUpDTO", new SignUpDto());
        return "auth/signup";
    }

    /**
     * 접근 거부 페이지
     */
    @GetMapping("/denied")
    public String denied() {
        return "auth/access_denied";
    }

    /**
     * Guest URL변환 페이지
     */
    @GetMapping("/main")
    public String guestMain() {
        return "home";
    }

    /**
     * 유저 URL 관리 페이지
     */
    @GetMapping("/user/main")
    public String userMain(Model model, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        model.addAttribute("userData", userService.findUserMainDataById(userDetails.getId()));
        return "user/main";
    }

    /**
     * 유저 정보 페이지
     */
    @GetMapping("/user/setting/info")
    public String userInfo(Model model, Authentication authentication) {
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        model.addAttribute("info", userService.findUserByEmail(userDetails.getUsername()));
        return "user/info";
    }

    /**
     * 유저 정보 변경 페이지
     */
    @GetMapping("/user/setting/editInfo")
    public String userEditInfo(Model model, Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        model.addAttribute("info", userService.findUserByEmail(user.getEmail()));
        return "/user/edit_info";
    }

    /**
     * 유저 삭제 페이지
     */
    @GetMapping("/user/setting/delete")
    public String userDelete() {
        return "/user/delete";
    }

    /**
     * 패스워드 변경 페이지
     */
    @GetMapping("/user/setting/editPassword")
    public String userEditPassword() {
        return "/user/edit_password";
    }

    /**
     * 닉네임 변경 페이지
     */
    @GetMapping("/user/setting/editNickname")
    public String userEditNickname(Model model, Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        model.addAttribute("nickname", userService.findUserByEmail(user.getEmail()).getNickname());
        return "/user/edit_nickname";
    }

    /**
     * 관리자 관리 페이지
     */
    @GetMapping("/admin/main")
    public String adminMain() {
        return "/admin/main";
    }

    @GetMapping("/admin/userManage")
    public String adminUserManage() {
        return "/admin/user_manage";
    }

    @GetMapping("/admin/urlManage")
    public String adminUrlManage() {
        return "/admin/url_manage";
    }

    @GetMapping("/admin/banUrlManage")
    public String adminBanUrlManage() {
        return "/admin/ban_url_manage";
    }
}

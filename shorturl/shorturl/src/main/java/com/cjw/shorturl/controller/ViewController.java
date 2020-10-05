package com.cjw.shorturl.controller;

import java.security.Principal;

import javax.validation.Valid;

import com.cjw.shorturl.dto.SignUpDTO;

import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.security.MyUserDetails;
import com.cjw.shorturl.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {
	private final LoginServiceImpl loginService;

	/**
	 * 회원가입 페이지
	 */
	@GetMapping("/auth/signUp")
	public String signUp(Model model) {
		model.addAttribute("signUpDTO", new SignUpDTO());
		return "auth/signup";
	}

	/**
	 * 접근 거부 페이지
	 */
	@GetMapping("/auth/denied")
	public String denied() {
		return "auth/access_denied";
	}

	/**
	 * Guest URL변환 페이지
	 */
	@GetMapping("/guest/main")
	public String guestMain() {
		return "home";
	}

	/**
	 * 유저 URL 관리 페이지
	 */
	@GetMapping("/user/main")
	public String userMain() {
		return "user/main";
	}

	/**
	 * 관리자 관리 페이지
	 */
	@GetMapping("/admin/main")
	public String adminMain() {
		return "admin/main";
	}

	/**
	 * 유저 정보 페이지
	 */
	@GetMapping("/user/setting/info")
	public String userInfo(Model model, Authentication authentication) {
		MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
		model.addAttribute("info", loginService.findUserByEmail(userDetails.getUsername()));
        return "user/info";
	}

	/**
	 * 유저 정보 변경 페이지
	 */
	@GetMapping("/user/setting/editInfo")
    public String userEditInfo(Model model, Authentication authentication){
		MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
		model.addAttribute("info", loginService.findUserByEmail(userDetails.getUsername()));
		return "/user/edit_info";
    }

	/**
	 * 유저 삭제 페이지
	 */
	@GetMapping("/user/setting/delete")
    public String userDelete(){
	    return "/user/delete";
    }

	/**
	 * 패스워드 변경 페이지
	 */
	@GetMapping("/user/setting/editPassword")
    public String userEditPassword(){
	    return "/user/edit_password";
    }

	/**
	 * 닉네임 변경 페이지
	 */
    @GetMapping("/user/setting/editNickname")
    public String userEditNickname(Model model, Authentication authentication){
		MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
		model.addAttribute("nickname", loginService.findUserByEmail(userDetails.getUsername()).getNickname());
	    return "/user/edit_nickname";
    }
}

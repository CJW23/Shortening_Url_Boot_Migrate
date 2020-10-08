package com.cjw.shorturl.controller;

import com.cjw.shorturl.ConstConfig;
import com.cjw.shorturl.dto.EditPasswordDTO;
import com.cjw.shorturl.dto.UserSettingResponseDTO;
import com.cjw.shorturl.exception.SamePasswordException;
import com.cjw.shorturl.exception.WrongCurrentPasswordException;
import com.cjw.shorturl.security.MyUserDetails;
import com.cjw.shorturl.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor    //선언한 객체들에 대한 생성자 자동 생성
@Slf4j
public class UserSettingController {
    private final UserSettingService userSettingService;

    @PutMapping("/user/setting/editInfo")
    public UserSettingResponseDTO editInfo(Authentication authentication, @RequestParam String name) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        log.info(user.getId().toString());
        userSettingService.updateUserName(user.getId(), name);
        return new UserSettingResponseDTO("true");
    }

    @GetMapping("/user/setting/checkNickname")
    public UserSettingResponseDTO checkNickname(@RequestParam String nickname) {
        return userSettingService.checkNickname(nickname) ?
                new UserSettingResponseDTO("true") :
                new UserSettingResponseDTO("false");
    }

    @PutMapping("/user/setting/editNickname")
    public UserSettingResponseDTO editNickname(Authentication authentication, @RequestParam String nickname) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.updateUserNickname(user.getId(), nickname);
        return new UserSettingResponseDTO("true");
    }

    @PutMapping("/user/setting/editPassword")
    public UserSettingResponseDTO editPassword(Authentication authentication, EditPasswordDTO editPasswordDTO) throws WrongCurrentPasswordException, SamePasswordException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.changePassword(user.getId(), editPasswordDTO.getCurrentPassword(), editPasswordDTO.getNewPassword());
        return new UserSettingResponseDTO("변경 완료", ConstConfig.CORRECT_PASSWORD.getVal());
    }

    @DeleteMapping("/user/setting/delete")
    public UserSettingResponseDTO deleteUser(HttpServletResponse response, Authentication authentication, @RequestParam String password) throws WrongCurrentPasswordException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.deleteUser(user.getId(), password);

        authentication = null;
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new UserSettingResponseDTO("삭제 완료", ConstConfig.DELETE_COMPLETE.getVal());
    }
}

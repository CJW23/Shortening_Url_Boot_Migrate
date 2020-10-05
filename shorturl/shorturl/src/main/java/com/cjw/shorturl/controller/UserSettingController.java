package com.cjw.shorturl.controller;

import com.cjw.shorturl.dto.UserSettingResponseDTO;
import com.cjw.shorturl.security.MyUserDetails;
import com.cjw.shorturl.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor    //선언한 객체들에 대한 생성자 자동 생성
@Slf4j
public class UserSettingController {
    private final UserSettingService userSettingService;

    @PutMapping("/users/setting/editInfo")
    public UserSettingResponseDTO editInfo(Authentication authentication, @RequestParam String name){
        MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
        log.info(user.getId().toString());
        userSettingService.updateUserName(user.getId(), name);
        return new UserSettingResponseDTO("TRUE");
    }
}

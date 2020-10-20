package com.cjw.shorturl.controller;

import com.cjw.shorturl.ConstConfig;
import com.cjw.shorturl.dto.*;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.exception.MakeRandomException;
import com.cjw.shorturl.exception.SamePasswordException;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.exception.WrongCurrentPasswordException;
import com.cjw.shorturl.security.MyUserDetails;
import com.cjw.shorturl.service.UrlServiceImpl;
import com.cjw.shorturl.service.UserServiceImpl;
import com.cjw.shorturl.service.UserSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor    //선언한 객체들에 대한 생성자 자동 생성
@Slf4j
public class UserController {
    private final UserSettingService userSettingService;
    private final UserServiceImpl userService;
    private final UrlServiceImpl urlService;

    @PostMapping("/user/create")
    public List<UserMainUrlDTO> userCreate(CreateUserUrlDTO userUrl, Authentication authentication) throws MakeRandomException, UrlException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        urlService.saveUserUrl(user.getId(), userUrl);
        return userService.findUrlListByUserId(user.getId());
    }

    @DeleteMapping("/user/url/delete")
    public List<UserMainUrlDTO> urlDelete(@RequestParam(value="deleteList[]") List<Long> deleteList, Authentication authentication){
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userService.removeUrlById(deleteList);
        return userService.findUrlListByUserId(user.getId());
    }

    @GetMapping("/user/data/total")
    public UserTotalDataDTO userTotal(Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        return userService.findTotalUrlData(user.getId());
    }

    @GetMapping("/user/data/url/{id}")
    public List<DayChartDTO> urlAccessChart(@PathVariable Long id) {
        //api 보안은??
        return userService.findUrlAccessData(id);
    }

    @GetMapping("/user/data/link/{id}")
    public List<LinkChartDTO> urlLinkChart(@PathVariable Long id){
        return userService.findUrlLinkData(id);
    }

    @GetMapping("/user/url/detail/{id}")
    public UrlDetailResponse urlDetail(Authentication authentication, @PathVariable Long id) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        return urlService.findUrlDetail(user.getId(), id);
    }

    @PutMapping("/user/setting/editInfo")
    public UserSettingResponse editInfo(Authentication authentication, @RequestParam String name) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.updateUserName(user.getId(), name);
        return new UserSettingResponse("true");
    }

    @GetMapping("/user/setting/checkNickname")
    public UserSettingResponse checkNickname(@RequestParam String nickname) {
        return userSettingService.checkNickname(nickname) ?
                new UserSettingResponse("true") :
                new UserSettingResponse("false");
    }

    @PutMapping("/user/setting/editNickname")
    public UserSettingResponse editNickname(Authentication authentication, @RequestParam String nickname) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.updateUserNickname(user.getId(), nickname);
        return new UserSettingResponse("true");
    }

    @PutMapping("/user/setting/editPassword")
    public UserSettingResponse editPassword(Authentication authentication, EditPasswordDTO editPasswordDTO) throws WrongCurrentPasswordException, SamePasswordException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.changePassword(user.getId(), editPasswordDTO.getCurrentPassword(), editPasswordDTO.getNewPassword());
        return new UserSettingResponse("변경 완료", ConstConfig.CORRECT_PASSWORD.getVal());
    }

    @DeleteMapping("/user/setting/delete")
    public UserSettingResponse deleteUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication, @RequestParam String password) throws WrongCurrentPasswordException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userSettingService.deleteUser(user.getId(), password);
        authentication.setAuthenticated(false);     //인증 제거
        return new UserSettingResponse("삭제 완료", ConstConfig.DELETE_COMPLETE.getVal());
    }
}

package com.cjw.shorturl.controller;

import com.cjw.shorturl.ConstConfig;
import com.cjw.shorturl.dto.*;
import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.exception.MakeRandomException;
import com.cjw.shorturl.exception.SamePasswordException;
import com.cjw.shorturl.exception.UrlException;
import com.cjw.shorturl.exception.WrongCurrentPasswordException;
import com.cjw.shorturl.security.MyUserDetails;
import com.cjw.shorturl.service.AdminService;
import com.cjw.shorturl.service.UrlServiceImpl;
import com.cjw.shorturl.service.UserServiceImpl;
import com.cjw.shorturl.service.UserSettingService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
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
    private final AdminService adminService;
    private final EntityManager em;

    @PostMapping("/user/create")
    public List<UserMainUrlDto> userCreate(CreateUserUrlDto userUrl, Authentication authentication) throws MakeRandomException, UrlException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        urlService.saveUserUrl(user.getId(), userUrl);
        return userService.findUrlListByUserId(user.getId());
    }

    @DeleteMapping("/user/url/delete")
    public List<UserMainUrlDto> urlDelete(@RequestParam(value = "deleteList[]") List<Long> deleteList, Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        userService.removeUrlById(deleteList);
        return userService.findUrlListByUserId(user.getId());
    }

    @GetMapping("/user/data/total")
    public UserTotalDataDto userTotal(Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        return userService.findTotalUrlData(user.getId());
    }

    @GetMapping("/user/data/url/{id}")
    public List<DayChartDto> urlAccessChart(@PathVariable Long id) {
        //api 보안은??
        return userService.findUrlAccessData(id);
    }

    @GetMapping("/user/data/link/{id}")
    public List<LinkChartDto> urlLinkChart(@PathVariable Long id) {
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
    public UserSettingResponse editPassword(Authentication authentication, EditPasswordDto editPasswordDTO) throws WrongCurrentPasswordException, SamePasswordException {
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

    @GetMapping("/admin/testMake")
    @Transactional
    public String testMake() {
        for (long i = 10; i < 100; i++) {
            /*User user = new User();
            user.setEmail("test" + i);
            user.setPassword("test" + i);
            user.setNickname("test" + i);
            user.setName("test" + i);
            user.setRole("USER");
            user.setPhone("awd");*/
            Url url = new Url();
            url.setOriginalUrl("test" + i);
            url.setShortUrl("test" + i);
            url.setId(i);
            em.persist(url);
        }
        return "ok";
    }
    /*@GetMapping("/a/userManage")
    public PageInfo<User> adminUserManage(@ModelAttribute UserSearchDto search,
                                          @RequestParam(required = false, defaultValue = "1") int pageNo, Model model) throws Exception {
        //Page<User> p = adminService.getUserList(pageNo, search);
        model.addAttribute("users", p);
        return new PageInfo<>(p);
    }*/
}

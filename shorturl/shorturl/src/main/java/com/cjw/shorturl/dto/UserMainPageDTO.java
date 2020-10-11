package com.cjw.shorturl.dto;

import java.util.List;

import com.cjw.shorturl.entity.Url;
import com.cjw.shorturl.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserMainPageDTO {
    List<Url> urlList;
    int totalUrlNum;
    int accessUrlNum;
    String userRole;
    String userName;

    public static UserMainPageDTO makeUserMainPage(User user){
        UserMainPageDTO userMainPage = new UserMainPageDTO();
        userMainPage.setUrlList(user.getUrls());
        userMainPage.setTotalUrlNum(user.getUrls().size());
        userMainPage.setAccessUrlNum(user.totalAccessUrlCount());
        userMainPage.setUserRole(user.getRole());
        userMainPage.setUserName(user.getName());
        return userMainPage;
    }
}

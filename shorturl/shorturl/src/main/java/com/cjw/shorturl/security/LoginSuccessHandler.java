package com.cjw.shorturl.security;

import com.cjw.shorturl.entity.User;
import com.cjw.shorturl.service.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectSt = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
        String auth = ((List<GrantedAuthority>) user.getAuthorities()).get(0).getAuthority();

        //권한에 따른 페이지 이동
        switch (auth){
            case "ROLE_USER":
                redirectSt.sendRedirect(request, response,"/user/main");
                break;
            case "ROLE_ADMIN":
                redirectSt.sendRedirect(request, response, "/admin/main");
                break;
        }
    }
}

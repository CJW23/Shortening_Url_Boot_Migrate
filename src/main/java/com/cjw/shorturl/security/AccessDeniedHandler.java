package com.cjw.shorturl.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    private final RedirectStrategy redirectSt = new DefaultRedirectStrategy();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(accessDeniedException != null){
            //SecurityContextHolder -> Authentication 관리
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String auth = ((List<GrantedAuthority>)((MyUserDetails) authentication.getPrincipal()).getAuthorities()).get(0).getAuthority();
            switch (auth){
                case "ROLE_USER":
                    redirectSt.sendRedirect(request,response,"/user/main");
                    break;
                case "ROLE_ADMIN":
                    redirectSt.sendRedirect(request,response,"/admin/main");
                    break;
                default:
                    redirectSt.sendRedirect(request,response, "/login");
            }
        }
    }
}

package com.cjw.shorturl.controller;

import com.cjw.shorturl.dto.AdminResponse;
import com.cjw.shorturl.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @DeleteMapping("/admin/user/{id}")
    public AdminResponse deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUser(id);
        return new AdminResponse("true");
    }

    @PutMapping("/admin/giveAuth/{id}")
    public AdminResponse giveAuth(@PathVariable("id") Long id) {
        adminService.giveAuth(id);
        return new AdminResponse("true");
    }

    @PutMapping("/admin/withdrawAuth/{id}")
    public AdminResponse withdrawAuth(@PathVariable("id") Long id) {
        adminService.withdrawAuth(id);
        return new AdminResponse("true");
    }

    @DeleteMapping("/admin/url/{id}")
    public AdminResponse deleteUrl(@PathVariable("id") Long id){
        adminService.deleteUrl(id);
        return new AdminResponse("true");
    }

    @DeleteMapping("/admin/banUrl/{id}")
    public AdminResponse deleteBanUrl(@PathVariable("id") Long id){
        adminService.deleteBanUrl(id);
        return new AdminResponse("true");
    }
}

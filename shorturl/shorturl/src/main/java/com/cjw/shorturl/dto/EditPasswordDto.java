package com.cjw.shorturl.dto;

import lombok.Data;

@Data
public class EditPasswordDto {
    private String currentPassword;
    private String newPassword;
    private String newConfirmPassword;
}

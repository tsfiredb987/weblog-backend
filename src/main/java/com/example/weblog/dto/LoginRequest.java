package com.example.weblog.dto;

import lombok.Data;

// 登入請求 DTO
@Data
public class LoginRequest {
    private String username;
    private String password;
}
package com.ytty.raja.service;

import com.ytty.raja.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto attemptLogin(String email, String password);
}

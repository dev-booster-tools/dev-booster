package com.ytty.raja.api.service;

import com.ytty.raja.api.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto attemptLogin(String email, String password);
}

package com.ytty.demoapp.raja.controller;

import com.ytty.raja.api.dto.LoginRequestDto;
import com.ytty.raja.api.dto.LoginResponseDto;
import com.ytty.raja.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.ROOT_PATH)
@RequiredArgsConstructor
public class AuthController {

    public static final String ROOT_PATH = "api/auth";
    private static final String LOGIN_PATH = "login";

    private final AuthService authService;

    @PostMapping(LOGIN_PATH)
    public LoginResponseDto login(@RequestBody @Validated LoginRequestDto request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}

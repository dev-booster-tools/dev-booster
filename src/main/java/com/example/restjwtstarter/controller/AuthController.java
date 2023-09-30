package com.example.restjwtstarter.controller;

import com.example.restjwtstarter.model.LoginRequest;
import com.example.restjwtstarter.model.LoginResponse;
import com.example.restjwtstarter.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AuthController.ROOT_PATH)
@RequiredArgsConstructor
public class AuthController {

    public static final String ROOT_PATH = "api/auth";
    private static final String LOGIN_PATH = "login";

    private final JwtIssuer jwtIssuer;

    @PostMapping(LOGIN_PATH)
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        String token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}

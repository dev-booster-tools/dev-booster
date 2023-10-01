package com.ytty.restjwtstarter.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {

    private final String accessToken;
    private final Long userId;
    private final List<String> roles;
}

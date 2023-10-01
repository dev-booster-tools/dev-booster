package com.ytty.raja.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LoginResponseDto {

    private final String accessToken;
    private final Long userId;
    private final List<String> roles;
}

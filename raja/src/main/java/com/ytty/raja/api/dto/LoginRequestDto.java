package com.ytty.raja.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {

    private String email;
    private String password;
}

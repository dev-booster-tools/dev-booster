package com.ytty.raja.controller;

import com.ytty.raja.security.UserPrincipal;
import com.ytty.raja.security.annotation.HasRoleAdmin;
import com.ytty.raja.security.annotation.HasRoleUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExampleController.ROOT_PATH)
public class ExampleController {

    public static final String ROOT_PATH = "api";
    private static final String USER_PATH = "user";
    private static final String ADMIN_PATH = "admin";

    @HasRoleUser
    @GetMapping(USER_PATH)
    public String helloUser(@AuthenticationPrincipal UserPrincipal principal) {
        return "Hello User: " + principal.getEmail() + " User ID: " + principal.getUserId();
    }

    @HasRoleAdmin
    @GetMapping(ADMIN_PATH)
    public String helloAdmin() {
        return "Hello Admin";
    }
}

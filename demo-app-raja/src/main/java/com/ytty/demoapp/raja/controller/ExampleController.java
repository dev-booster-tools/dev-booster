package com.ytty.demoapp.raja.controller;

import com.ytty.raja.api.annotation.HasRoleAdmin;
import com.ytty.raja.api.annotation.HasRoleUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExampleController.ROOT)
public class ExampleController {

    public static final String ROOT = "api";
    private static final String USER_PATH = "user";
    private static final String ADMIN_PATH = "admin";

    @HasRoleUser
    @GetMapping(USER_PATH)
    public String helloUser() {
        return "Hello User";
    }

    @HasRoleAdmin
    @GetMapping(ADMIN_PATH)
    public String helloAdmin() {
        return "Hello Admin";
    }

}

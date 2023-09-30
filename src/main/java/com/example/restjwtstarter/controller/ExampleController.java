package com.example.restjwtstarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExampleController.ROOT_PATH)
public class ExampleController {

    public static final String ROOT_PATH = "api";
    private static final String USER_PATH = "user";
    private static final String ADMIN_PATH = "admin";

    @GetMapping
    public String helloRoot() {
        return "Hello Root";
    }

    @GetMapping(USER_PATH)
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping(ADMIN_PATH)
    public String helloAdmin() {
        return "Hello Admin";
    }
}

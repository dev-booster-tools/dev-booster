package com.ytty.restjwtstarter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytty.restjwtstarter.model.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void everyOne_canPassLoginWithCorrectCredentials() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("admin@mail.ru")
                .password("test")
                .build();

        api.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void nobody_canLoginWithWrongCredentials() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("fake@mail.ru")
                .password("wrong_password")
                .build();

        api.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
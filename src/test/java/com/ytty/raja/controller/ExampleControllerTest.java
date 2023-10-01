package com.ytty.raja.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExampleControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/api/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedInUser_shouldSeeSecurityEndpointForUsers() throws Exception {
        api.perform(get("/api/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void loggedInUser_shouldNotSeeSecurityEndpointForAdmins() throws Exception {
        api.perform(get("/api/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockAdminUser
    void loggedInAdmin_shouldSeeSecurityEndpointForAdmins() throws Exception {
        api.perform(get("/api/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockAdminUser
    void loggedInAdmin_shouldNotSeeSecurityEndpointForUsers() throws Exception {
        api.perform(get("/api/user"))
                .andExpect(status().isForbidden());
    }

}
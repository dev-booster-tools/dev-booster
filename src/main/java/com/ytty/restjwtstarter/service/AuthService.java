package com.ytty.restjwtstarter.service;

import com.ytty.restjwtstarter.model.LoginResponse;

public interface AuthService {
    LoginResponse attemptLogin(String email, String password);
}

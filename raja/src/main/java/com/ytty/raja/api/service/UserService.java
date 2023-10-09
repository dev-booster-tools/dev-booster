package com.ytty.raja.api.service;

import com.ytty.raja.api.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String username);
}

package com.ytty.raja.service;

import com.ytty.raja.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String username);
}

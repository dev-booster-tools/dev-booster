package com.ytty.raja.api.repository;

import com.ytty.raja.api.model.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> findByEmail(String email);
}

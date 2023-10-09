package com.ytty.raja.repository;

import com.ytty.raja.model.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> findByEmail(String email);
}

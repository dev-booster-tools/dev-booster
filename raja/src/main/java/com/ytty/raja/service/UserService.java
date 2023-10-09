package com.ytty.raja.service;

import com.ytty.raja.model.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String username);
}

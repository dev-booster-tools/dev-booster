package com.ytty.restjwtstarter.service;

import com.ytty.restjwtstarter.model.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String username);
}

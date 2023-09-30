package com.ytty.restjwtstarter.service.impl;

import com.ytty.restjwtstarter.model.UserEntity;
import com.ytty.restjwtstarter.repository.UserRepo;
import com.ytty.restjwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public Optional<UserEntity> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

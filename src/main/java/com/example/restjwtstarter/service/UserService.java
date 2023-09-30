package com.example.restjwtstarter.service;

import com.example.restjwtstarter.model.UserEntity;
import com.example.restjwtstarter.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Optional<UserEntity> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

package com.ytty.raja.service.impl;

import com.ytty.raja.model.UserEntity;
import com.ytty.raja.repository.UserRepo;
import com.ytty.raja.service.UserService;
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

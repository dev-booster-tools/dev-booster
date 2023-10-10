package com.ytty.demoapp.raja.service;

import com.ytty.raja.api.model.User;
import com.ytty.raja.api.repository.UserRepo;
import com.ytty.raja.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

package com.ytty.demoapp.raja.repository;

import com.ytty.demoapp.raja.model.UserEntity;
import com.ytty.raja.api.repository.UserRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoImp extends UserRepo, JpaRepository<UserEntity, Long> {
}

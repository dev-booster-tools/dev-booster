package com.ytty.app.repository;

import com.ytty.app.model.UserEntity;
import com.ytty.raja.api.repository.UserRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoImp extends UserRepo, JpaRepository<UserEntity, Long> {
}

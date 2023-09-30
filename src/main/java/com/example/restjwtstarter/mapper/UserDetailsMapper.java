package com.example.restjwtstarter.mapper;

import com.example.restjwtstarter.model.UserEntity;
import com.example.restjwtstarter.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsMapper {

    public UserDetails map(UserEntity userEntity) {
        return UserPrincipal.builder()
                .userId(userEntity.getId())
                .email(userEntity.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(userEntity.getRole())))
                .password(userEntity.getPassword())
                .build();
    }
}

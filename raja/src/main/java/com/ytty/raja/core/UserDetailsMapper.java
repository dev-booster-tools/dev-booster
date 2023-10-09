package com.ytty.raja.core;

import com.ytty.raja.api.model.User;
import com.ytty.raja.api.model.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class UserDetailsMapper {

    public UserDetails map(User userEntity) {
        return UserPrincipal.builder()
                .userId(userEntity.getId())
                .email(userEntity.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(userEntity.getRole().name())))
                .password(userEntity.getPassword())
                .build();
    }
}

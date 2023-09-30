package com.ytty.restjwtstarter.security;

import com.ytty.restjwtstarter.mapper.UserDetailsMapper;
import com.ytty.restjwtstarter.model.UserEntity;
import com.ytty.restjwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByEmail(username).orElseThrow();
        return mapper.map(user);
    }
}

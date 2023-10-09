package com.ytty.raja.core;

import com.ytty.raja.api.model.User;
import com.ytty.raja.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;
    private final UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return mapper.map(user);
    }
}

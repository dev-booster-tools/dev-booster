package com.ytty.restjwtstarter.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {

    /**
     * Secret key for JWT
     */
    private String secretKey;

    /**
     * Expiration duration in seconds
     */
    private long expirationSeconds;
}

package com.spring_boot_security_jwt_auth_example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param secret                   base64-encoded HMAC key for signing JWTs
 * @param accessTokenExpirationMs  access token lifetime in milliseconds
 * @param refreshTokenExpirationMs refresh token lifetime in milliseconds
 */

@ConfigurationProperties("jwt")
public record JwtProperties(String secret, long accessTokenExpirationMs, long refreshTokenExpirationMs) {

}

package com.authservice.auth_service.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateAccessToken(Authentication authentication);

    String generateRefreshToken(Authentication authentication);
}

package com.authservice.auth_service.service.impl;

import com.authservice.auth_service.dto.LoginRequest;
import com.authservice.auth_service.dto.LoginResponse;
import com.authservice.auth_service.service.JwtService;
import com.authservice.auth_service.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        String accessToken =
                jwtService.generateAccessToken(authentication);

        String refreshToken =
                jwtService.generateRefreshToken(authentication);

        LoginResponse response = new LoginResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(900);

        return response;
    }
}

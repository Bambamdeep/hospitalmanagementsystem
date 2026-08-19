package com.authservice.auth_service.service;

import com.authservice.auth_service.dto.LoginRequest;
import com.authservice.auth_service.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}


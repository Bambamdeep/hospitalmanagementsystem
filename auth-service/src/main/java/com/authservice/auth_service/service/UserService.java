package com.authservice.auth_service.service;

import com.authservice.auth_service.dto.RegisterRequest;
import com.authservice.auth_service.entity.User;

public interface UserService {

    User register(RegisterRequest request);
}

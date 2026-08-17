package com.authservice.auth_service.service.impl;

import com.authservice.auth_service.dto.RegisterRequest;
import com.authservice.auth_service.entity.Role;
import com.authservice.auth_service.entity.User;
import com.authservice.auth_service.exception.UserAlreadyExistsException;
import com.authservice.auth_service.repository.UserRepository;
import com.authservice.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User register(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .enabled(true)
                .build();

        return repository.save(user);
    }
    }


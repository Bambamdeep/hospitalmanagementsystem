package com.authservice.auth_service.controller;

import com.authservice.auth_service.dto.*;
import com.authservice.auth_service.entity.User;
import com.authservice.auth_service.service.LoginService;
import com.authservice.auth_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final LoginService loginService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>>register(@Valid @RequestBody RegisterRequest request) {

        User user = userService.register(request);

        RegisterResponse response = RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

        ApiResponse<RegisterResponse> apiResponse = ApiResponse.<RegisterResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(response)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = loginService.login(request);

        return ResponseEntity.ok(response);
    }
}

package com.authservice.auth_service.dto;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private long expiresIn;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken,
                         String refreshToken,
                         String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}

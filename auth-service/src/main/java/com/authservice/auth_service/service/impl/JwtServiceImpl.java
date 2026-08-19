package com.authservice.auth_service.service.impl;

import com.authservice.auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    @Override
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:9000")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(900))
                .subject(authentication.getName())
                .claim("scope", "openid profile email")
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();
    }

    @Override
    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:9000")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(604800))
                .subject(authentication.getName())
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        ).getTokenValue();
    }
}

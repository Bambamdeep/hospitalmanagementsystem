package com.authservice.auth_service.configuration;

import org.springframework.context.annotation.Configuration;
import java.time.Duration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
@Configuration
public class AuthorizationServerConfig {



    @Bean
    public TokenSettings tokenSettings() {


        return TokenSettings.builder()

                // Access token validity
                .accessTokenTimeToLive(
                        Duration.ofMinutes(15)
                )

                // Refresh token validity
                .refreshTokenTimeToLive(
                        Duration.ofDays(7)
                )

                // Reuse refresh token
                .reuseRefreshTokens(false)

                .build();

    }



    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {


        return AuthorizationServerSettings.builder()

                // Authorization Server URL
                .issuer(
                        "http://localhost:9000"
                )

                .build();

    }

}

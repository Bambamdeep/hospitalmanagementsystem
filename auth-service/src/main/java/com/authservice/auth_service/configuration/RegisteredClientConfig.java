package com.authservice.auth_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.UUID;

@Configuration
public class RegisteredClientConfig {
    @Bean
    public RegisteredClientRepository registeredClientRepository(
            PasswordEncoder passwordEncoder,
            TokenSettings tokenSettings) {

        RegisteredClient registeredClient =
                RegisteredClient.withId(UUID.randomUUID().toString())

                        .clientId("hospital-client")

                        .clientSecret(
                                passwordEncoder.encode("hospital-secret")
                        )

                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )

                        .authorizationGrantType(
                                AuthorizationGrantType.AUTHORIZATION_CODE
                        )

                        .authorizationGrantType(
                                AuthorizationGrantType.REFRESH_TOKEN
                        )

                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )

                        .redirectUri(
                                "http://127.0.0.1:8080/login/oauth2/code/hospital-client"
                        )

                        .scope(OidcScopes.OPENID)
                        .scope("read")
                        .scope("write")

                        .tokenSettings(tokenSettings)

                        .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }
}

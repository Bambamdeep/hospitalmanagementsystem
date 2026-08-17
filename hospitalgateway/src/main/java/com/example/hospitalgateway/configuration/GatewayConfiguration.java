package com.example.hospitalgateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean

    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("auth-service", r -> r
                        .path("/auth/**")
                        .uri("lb://AUTH-SERVICE"))

                .route("patient-service", r -> r
                        .path("/patient/**")
                        .uri("lb://PATIENT"))

                .route("doctor-service", r -> r
                        .path("/doctor/**")
                        .uri("lb://DOCTOR"))

                .route("appointment-service", r -> r
                        .path("/appointment/**")
                        .uri("lb://APPOINTMENT"))

                .route("department-service", r -> r
                        .path("/department/**")
                        .uri("lb://DEPARTMENT"))

                .route("billing-service", r -> r
                        .path("/bill/**")
                        .uri("lb://BILLING"))

                .build();
    }

}

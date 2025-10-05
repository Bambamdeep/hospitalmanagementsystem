package com.example.hospitalgateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return (RouteLocator) builder.routes()
				.route(p -> p.path("/patient/***").uri("lb://patient/patient"))
				.route(p -> p.path("/doctor/***").uri("lb://doctor/doctor"))
				.route(p -> p.path("/appointment/***").uri("lb://appointment/appointment"))
				.route(p -> p.path("/department/***").uri("lb://department/department"))
				.route(p -> p.path("/bill/***").uri("lb://billing/bill"))
				.build();

	}

}

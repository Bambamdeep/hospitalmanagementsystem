package com.hospitalmanagement.billing.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI customApi() {
		return new OpenAPI().info(new Info().title("Billing Service Api").description("By Apollo"))
				.servers(Arrays.asList(new Server().url("http//localhost:8085")))
				.tags(Arrays.asList(new Tag().name("Billing Api")));
	}
}

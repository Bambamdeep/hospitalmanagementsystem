package com.example.doctor.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info().title("Doctor Service Api")
						.description("By Apollo")).servers(Arrays.asList(new Server().url("http//localhost:8082")))
						;
	}
}

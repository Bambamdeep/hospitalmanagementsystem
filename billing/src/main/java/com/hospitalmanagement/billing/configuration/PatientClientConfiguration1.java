package com.hospitalmanagement.billing.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration

public class PatientClientConfiguration1 {
	  @Bean
	    public RequestInterceptor basicAuthRequestInterceptor() {
	        return requestTemplate -> {
	            String username = "kumar";
	            String password = "123";
	            String auth = username + ":" + password;
	            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
	            String authHeader = "Basic " + new String(encodedAuth);
	            requestTemplate.header("Authorization", authHeader);
	        };
	    }
}

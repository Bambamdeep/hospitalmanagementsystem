package com.hospitalmanagement.appointment.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hospitalmanagement.appointment.exception.PatientServiceUnavailableException;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration

public class PatientClientConfiguration {
	private final ObjectProvider<HttpServletRequest> requestProvider;
	/*
	
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
	  @Bean
	    public ErrorDecoder errorDecoder() {
	        return (methodKey, response) -> {
	            if (response.status() == 503) {
	                return new PatientServiceUnavailableException ("Patient service is unavailable try AftersomeTime");
	            }
	            return new ErrorDecoder.Default().decode(methodKey, response);
	        };
	    }

*/
	public PatientClientConfiguration(ObjectProvider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Bean
	public RequestInterceptor requestInterceptor() {

		return template -> {

			HttpServletRequest request = requestProvider.getIfAvailable();

			if (request == null) {
				return;
			}

			String authorization = request.getHeader("Authorization");

			if (authorization != null) {
				template.header("Authorization", authorization);
			}
		};
	}
}

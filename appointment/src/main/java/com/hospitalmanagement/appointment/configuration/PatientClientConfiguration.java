package com.hospitalmanagement.appointment.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hospitalmanagement.appointment.exception.PatientServiceUnavailableException;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
public class PatientClientConfiguration {

	
	
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


}

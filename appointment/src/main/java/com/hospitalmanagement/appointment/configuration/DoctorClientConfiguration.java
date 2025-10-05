package com.hospitalmanagement.appointment.configuration;



/*
@Configuration
public class DoctorClientConfiguration {
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
*/
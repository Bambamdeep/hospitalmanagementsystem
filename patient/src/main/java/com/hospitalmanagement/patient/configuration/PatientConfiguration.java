package com.hospitalmanagement.patient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PatientConfiguration {
	/*
	@Bean
	public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/patient/all").hasRole("user")
				.requestMatchers("/patient/create", "/patient/delete", "/patient/update").hasRole("admin")
				.requestMatchers("/patient/id").hasRole("user")
				.requestMatchers("/v3/api-docs/***","/swagger-ui/***","/docs/***").permitAll()
				.anyRequest().authenticated());

		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
/*
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("deepak").password("1234").roles("admin").build();

		UserDetails user2 = User.withUsername("kumar").password("123").roles("user").build();

		return new InMemoryUserDetailsManager(user1, user2);

	}
    /*
	/*
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}
*/
	
	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
	    UserDetails user1 = User.withUsername("deepak")
	        .password(passwordEncoder.encode("1234")) 
	        .roles("admin")
	        .build();

	    UserDetails user2 = User.withUsername("kumar")
	        .password(passwordEncoder.encode("123")) 
	        .roles("user")
	        .build();

	    return new InMemoryUserDetailsManager(user1, user2);
	}
*/
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {

		http
				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth

						.requestMatchers(
								"/v3/api-docs/**",
								"/swagger-ui/**",
								"/swagger-ui.html"
						).permitAll()

						.anyRequest().authenticated()
				)

				.oauth2ResourceServer(oauth2 ->
						oauth2.jwt(Customizer.withDefaults())
				);

		return http.build();
	}
	}

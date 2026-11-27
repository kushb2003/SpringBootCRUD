package com.pratik.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Requires an authenticated principal for every endpoint exposed by the application,
 * including the state-changing /reg, /update and /delete routes.
 * Credentials are supplied through Spring Boot's standard spring.security.user.* properties
 * (or a randomly generated password when none is configured), so no secret is hardcoded here.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
		return http.build();
	}
}

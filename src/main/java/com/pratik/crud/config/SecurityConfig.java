package com.pratik.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Deny by default: every endpoint (/all, /reg, /update, /delete) requires an
 * authenticated caller with the ADMIN role. Credentials come only from
 * configuration (APP_ADMIN_USERNAME / APP_ADMIN_PASSWORD); startup fails if absent.
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().hasRole("ADMIN")
				.and()
			.httpBasic()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			// Stateless HTTP Basic JSON API: no session cookie, so CSRF tokens are not applicable.
			.csrf().disable();
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(
			@Value("${APP_ADMIN_USERNAME}") String username,
			@Value("${APP_ADMIN_PASSWORD}") String encodedPassword) {
		if (username == null || username.trim().isEmpty()
				|| encodedPassword == null || encodedPassword.trim().isEmpty()) {
			throw new IllegalStateException("APP_ADMIN_USERNAME and APP_ADMIN_PASSWORD must be configured");
		}
		UserDetails admin = User.withUsername(username)
				.password(encodedPassword)
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);
	}
}

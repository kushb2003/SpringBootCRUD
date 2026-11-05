package com.pratik.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Deny-by-default security: every endpoint requires an authenticated caller
 * holding the ADMIN role. Admin credentials come only from configuration
 * (APP_SECURITY_ADMIN_USERNAME / APP_SECURITY_ADMIN_PASSWORD, the password
 * given in encoded form, e.g. "{bcrypt}$2a$..."); the application fails to
 * start when they are absent.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.anyRequest().hasRole("ADMIN"))
			.httpBasic()
			.and()
			.formLogin().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			// Stateless HTTP Basic JSON API: no session cookie, so no CSRF-able ambient credential.
			.csrf().disable();
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(
			@Value("${app.security.admin.username}") String username,
			@Value("${app.security.admin.password}") String encodedPassword) {
		if (username == null || username.trim().isEmpty()
				|| encodedPassword == null || encodedPassword.trim().isEmpty()) {
			throw new IllegalStateException("app.security.admin.username and app.security.admin.password must be configured");
		}
		if (!encodedPassword.startsWith("{")) {
			throw new IllegalStateException("app.security.admin.password must be an encoded password with an id prefix, e.g. {bcrypt}...");
		}
		UserDetails admin = User.withUsername(username)
				.password(encodedPassword)
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);
	}
}

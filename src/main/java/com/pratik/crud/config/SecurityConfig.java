package com.pratik.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

/**
 * Requires an authenticated caller (HTTP Basic) for every endpoint, including the
 * state-changing /reg, /update/{id} and /delete operations. Credentials come from the
 * standard Spring Boot properties spring.security.user.name / spring.security.user.password
 * (supplied via environment/config); when unset, Spring Boot generates a random password,
 * so access fails closed. CSRF protection stays enabled using a cookie-based token.
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Spring Security 6 defaults to XOR-masked, deferred CSRF tokens. Keep the existing contract,
		// where clients echo the raw XSRF-TOKEN cookie value and the cookie is issued on every request.
		CsrfTokenRequestAttributeHandler csrfRequestHandler = new CsrfTokenRequestAttributeHandler();
		csrfRequestHandler.setCsrfRequestAttributeName(null);
		http
			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrf -> csrf
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.csrfTokenRequestHandler(csrfRequestHandler));
		return http.build();
	}
}

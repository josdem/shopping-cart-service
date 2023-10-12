package com.josdem.shopping.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (authorize) ->
                authorize
                    .requestMatchers("**/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers("/cart/**", "/login/**", "/products/**")
                    .hasAnyAuthority("SCOPE_write")
                    .anyRequest()
                    .authenticated())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    return http.build();
  }
}

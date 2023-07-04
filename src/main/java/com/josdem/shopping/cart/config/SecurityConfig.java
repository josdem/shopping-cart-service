package com.josdem.shopping.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
    return http.csrf()
        .disable()
        .authorizeExchange()
        .pathMatchers("/")
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin()
        .disable()
        .build();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user =
        User.builder().username("user").password("{noop}password").roles("USER").build();
    return new MapReactiveUserDetailsService(user);
  }
}

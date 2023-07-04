package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final ApplicationConfig applicationConfig;

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
        User.builder()
            .username(applicationConfig.getUsername())
            .password(applicationConfig.getPassword())
            .roles(Roles.USER.toString())
            .build();
    return new MapReactiveUserDetailsService(user);
  }
}

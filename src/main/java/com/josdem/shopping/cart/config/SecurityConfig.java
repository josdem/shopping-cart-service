package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.auth.BasicAuthenticationSuccessHandler;
import com.josdem.shopping.cart.auth.BearerTokenReactiveAuthenticationManager;
import com.josdem.shopping.cart.auth.ServerHttpBearerAuthenticationConverter;
import com.josdem.shopping.cart.model.Role;
import com.josdem.shopping.cart.security.SecurityToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final ApplicationConfig applicationConfig;
  private final SecurityToken securityToken;

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

    http.authorizeExchange()
        .pathMatchers("/login")
        .authenticated()
        .and()
        .addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
        .authorizeExchange()
        .pathMatchers("/api/**", "/products/**")
        .authenticated()
        .and()
        .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);

    return http.build();
  }

  @SuppressWarnings("deprecation")
  @Bean
  public MapReactiveUserDetailsService userDetailsRepository() {
    UserDetails user =
        User.withDefaultPasswordEncoder()
            .username(applicationConfig.getUsername())
            .password(applicationConfig.getPassword())
            .roles(Role.USER.toString(), Role.ADMIN.toString())
            .build();
    return new MapReactiveUserDetailsService(user);
  }

  private AuthenticationWebFilter basicAuthenticationFilter() {
    UserDetailsRepositoryReactiveAuthenticationManager authManager;
    AuthenticationWebFilter basicAuthenticationFilter;
    ServerAuthenticationSuccessHandler successHandler;

    authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository());
    successHandler = new BasicAuthenticationSuccessHandler(securityToken);

    basicAuthenticationFilter = new AuthenticationWebFilter(authManager);
    basicAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);

    return basicAuthenticationFilter;
  }

  private AuthenticationWebFilter bearerAuthenticationFilter() {
    AuthenticationWebFilter bearerAuthenticationFilter;
    Function<ServerWebExchange, Mono<Authentication>> bearerConverter;
    ReactiveAuthenticationManager authManager;

    authManager = new BearerTokenReactiveAuthenticationManager();
    bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
    bearerConverter = new ServerHttpBearerAuthenticationConverter();

    bearerAuthenticationFilter.setAuthenticationConverter(bearerConverter);
    bearerAuthenticationFilter.setRequiresAuthenticationMatcher(
        ServerWebExchangeMatchers.pathMatchers("/api/**", "/products/**"));

    return bearerAuthenticationFilter;
  }
}

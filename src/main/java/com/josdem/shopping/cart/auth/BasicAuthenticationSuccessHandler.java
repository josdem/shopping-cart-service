package com.josdem.shopping.cart.auth;

import com.josdem.shopping.cart.auth.jwt.JWTTokenService;
import com.josdem.shopping.cart.security.SecurityToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

  private final SecurityToken securityToken;

  @Override
  public Mono<Void> onAuthenticationSuccess(
      WebFilterExchange webFilterExchange, Authentication authentication) {
    ServerWebExchange exchange = webFilterExchange.getExchange();
    exchange
        .getResponse()
        .getHeaders()
        .add(HttpHeaders.AUTHORIZATION, getHttpAuthHeaderValue(authentication));
    return webFilterExchange.getChain().filter(exchange);
  }

  private String getHttpAuthHeaderValue(Authentication authentication) {
    String token = tokenFromAuthentication(authentication);
    log.info("token: {}", token);
    securityToken.setToken(token);
    return String.join(" ", "Bearer", token);
  }

  private String tokenFromAuthentication(Authentication authentication) {
    return JWTTokenService.generateToken(
        authentication.getName(), authentication.getCredentials(), authentication.getAuthorities());
  }
}

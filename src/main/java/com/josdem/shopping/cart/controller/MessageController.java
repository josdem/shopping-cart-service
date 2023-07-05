package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.FormattedMessage;
import com.josdem.shopping.cart.security.AuthResponse;
import com.josdem.shopping.cart.security.SecurityToken;
import com.josdem.shopping.cart.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {
  private final MessageService messageService;
  private final SecurityToken securityToken;

  @GetMapping("/login")
  public Mono<AuthResponse> login() {
    return Mono.just(new AuthResponse(securityToken.getToken()));
  }

  @GetMapping("/api/private")
  @PreAuthorize("hasRole('USER')")
  public Flux<FormattedMessage> privateMessage() {
    return messageService.getCustomMessage("User");
  }

  @GetMapping("/api/admin")
  public Flux<FormattedMessage> privateMessageAdmin() {
    return messageService.getCustomMessage("Admin");
  }

  @GetMapping("/api/guest")
  @PreAuthorize("hasRole('GUEST')")
  public Flux<FormattedMessage> privateMessageGuest() {
    return messageService.getCustomMessage("Guest");
  }
}

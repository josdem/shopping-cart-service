package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.FormattedMessage;
import com.josdem.shopping.cart.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class MessageController {
  private final MessageService messageService;

  @GetMapping("/")
  public Flux<FormattedMessage> hello() {
    return messageService.getDefaultMessage();
  }

  @GetMapping("/login")
  public Flux<FormattedMessage> login() {
    return messageService.getDefaultMessage();
  }

  @GetMapping("/api/private")
  @PreAuthorize("hasRole('USER')")
  public Flux<FormattedMessage> privateMessage() {
    return messageService.getCustomMessage("User");
  }

  @GetMapping("/api/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public Flux<FormattedMessage> privateMessageAdmin() {
    return messageService.getCustomMessage("Admin");
  }

  @GetMapping("/api/guest")
  @PreAuthorize("hasRole('GUEST')")
  public Flux<FormattedMessage> privateMessageGuest() {
    return messageService.getCustomMessage("Guest");
  }
}

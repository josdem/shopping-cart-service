package com.josdem.shopping.cart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

  @GetMapping("/")
  public Mono welcome() {
    return Mono.just("Welcome !");
  }
}

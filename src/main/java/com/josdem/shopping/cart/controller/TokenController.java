package com.josdem.shopping.cart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class TokenController {

    @PostMapping("/login")
    public Mono<String> generateToken() {
        return Mono.just(UUID.randomUUID().toString());
    }
}

package com.josdem.shopping.cart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class TokenController {

    @PostMapping("/")
    public Mono<String> generateToken() {
        return Mono.just(UUID.randomUUID().toString());
    }
}

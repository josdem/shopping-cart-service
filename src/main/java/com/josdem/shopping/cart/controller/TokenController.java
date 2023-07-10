package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TokenController {

    @GetMapping("/login")
    public Mono<AuthResponse> generateToken() {
        log.info("Generating token");
        return Mono.just(new AuthResponse());
    }
}

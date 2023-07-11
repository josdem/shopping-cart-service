package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.AuthRequest;
import com.josdem.shopping.cart.model.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TokenController {

    private final String USERNAME = "josdem";
    private final String PASSWORD = "12345678";

    @PostMapping(value = "/login", consumes = "application/json")
    public Mono<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
        log.info("Generating token from data: {}", authRequest.toString());
        if (authRequest.getUsername().equals(USERNAME) && authRequest.getPassword().equals(PASSWORD)) {
            return Mono.just(new AuthResponse());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}

package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TokenControllerTest {

    private final WebTestClient webTestClient;

    private final AuthRequest authRequest = new AuthRequest();

    @BeforeEach
    void setup() {
        authRequest.setUsername("josdem");
        authRequest.setPassword("12345678");
    }

    @Test
    @DisplayName("getting token")
    void shouldGetToken(TestInfo testInfo) {
        log.info("Running: {}", testInfo.getDisplayName());
        webTestClient
                .post()
                .uri("/login")
                .body(BodyInserters.fromValue(authRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.token").isNotEmpty();
    }

    @Test
    @DisplayName("getting unauthorized")
    void shouldGetUnauthorized(TestInfo testInfo) {
        log.info("Running: {}", testInfo.getDisplayName());
        webTestClient
                .post()
                .uri("/login")
                .body(BodyInserters.fromValue(new AuthRequest()))
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }
}

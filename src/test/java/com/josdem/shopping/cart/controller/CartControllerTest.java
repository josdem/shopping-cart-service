package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.config.ApplicationProperties;
import com.josdem.shopping.cart.model.Authorization;
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
class CartControllerTest {

    private final WebTestClient webTestClient;
    private final ApplicationProperties applicationProperties;

    private final Authorization authorization = new Authorization();

    @BeforeEach
    void setup() {
        authorization.setToken(applicationProperties.getToken());
    }

    @Test
    @DisplayName("getting cart")
    void shouldGetCart(TestInfo testInfo) {
        log.info("Running: {}", testInfo.getDisplayName());
        webTestClient
                .post()
                .uri("/cart/")
                .bodyValue(BodyInserters.fromValue(authorization))
                .exchange()
                .expectStatus()
                .isOk();
    }
}

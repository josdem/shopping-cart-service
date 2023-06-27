package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ProductControllerTest {

  private final WebTestClient webTestClient;

  @Test
  @DisplayName("getting products")
  void shouldGetProducts(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Product.class)
        .hasSize(2);
  }
}

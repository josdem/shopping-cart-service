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

import java.math.BigDecimal;

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

  @Test
  @DisplayName("getting product by id")
  void shouldGetProductById(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/100")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Product.class)
        .isEqualTo(new Product("100", "Nike Air Max 2023", new BigDecimal(210)));
  }

  @Test
  @DisplayName("getting product by id not found")
  void shouldGetProductByIdNotFound(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient.get().uri("/products/1").exchange().expectStatus().isNotFound();
  }

  @Test
  @DisplayName("expect access control allow origin header")
  void shouldValidateAccessControlAllowOrigin(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/")
        .exchange()
        .expectHeader()
        .value("Vary", origin -> origin.equals("*"));
  }
}

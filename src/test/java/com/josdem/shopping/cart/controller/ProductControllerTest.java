package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.config.ApplicationConfig;
import com.josdem.shopping.cart.model.Product;
import com.josdem.shopping.cart.security.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.TestInstance.Lifecycle;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@TestInstance(Lifecycle.PER_CLASS)
class ProductControllerTest {

  private final WebTestClient webTestClient;
  private final ApplicationConfig applicationConfig;
  private String token;

  private Map<String, String> bodyMap = new HashMap();

  @BeforeAll
  void setup() {
    bodyMap.put("username", "josdem");
    bodyMap.put("password", "12345678");
  }

  @BeforeEach
  void setup(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    AuthResponse response =
        webTestClient
            .post()
            .uri("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(bodyMap))
            .exchange()
            .returnResult(AuthResponse.class)
            .getResponseBody()
            .blockFirst();
    token = response.getToken();
  }

  @Test
  @DisplayName("getting products")
  void shouldGetProducts(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/")
        .headers(headers -> headers.setBearerAuth(token))
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
        .headers(headers -> headers.setBearerAuth(token))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Product.class)
        .isEqualTo(new Product("100", "Nike Air Max", new BigDecimal(1259.00)));
  }

  @Test
  @DisplayName("getting product by id not found")
  void shouldGetProductByIdNotFound(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/1")
        .headers(headers -> headers.setBearerAuth(token))
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  @DisplayName("expect access control allow origin header")
  void shouldValidateAccessControlAllowOrigin(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    webTestClient
        .get()
        .uri("/products/")
        .headers(headers -> headers.setBearerAuth(token))
        .exchange()
        .expectHeader()
        .value("Vary", origin -> origin.equals("*"));
  }
}

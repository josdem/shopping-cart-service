package com.josdem.shopping.cart.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SwaggerTest {

  private final WebTestClient webTestClient;

  @Test
  @DisplayName("getting swagger")
  void shouldGetSwagger() {
    webTestClient.get().uri("/webjars/swagger-ui/index.html").exchange().expectStatus().isOk();
  }
}

package com.josdem.shopping.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: Add test to validate allowed methods
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CartControllerTest {

  private final WebTestClient webTestClient;
  private final ApplicationProperties applicationProperties;
  private final MockMvc mockMvc;
  private final Authorization authorization = new Authorization();
  private final ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    authorization.setToken(applicationProperties.getToken());
  }

  @Test
  @DisplayName("getting cart")
  void shouldGetCart(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());

    mockMvc
        .perform(
            post("/cart/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorization)))
        .andExpect(status().isOk());
  }
}

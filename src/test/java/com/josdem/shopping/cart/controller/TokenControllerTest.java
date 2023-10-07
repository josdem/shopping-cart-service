package com.josdem.shopping.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josdem.shopping.cart.model.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TokenControllerTest {

  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;
  private final AuthRequest authRequest = new AuthRequest();

  @BeforeEach
  void setup() {
    authRequest.setUsername("josdem");
    authRequest.setPassword("12345678");
  }

  @Test
  @DisplayName("getting token")
  void shouldGetToken(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());

    mockMvc
        .perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(status().isOk());
  }
}

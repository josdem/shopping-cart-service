package com.josdem.shopping.cart.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.josdem.shopping.cart.util.OauthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ProductControllerTest {

  public static final String BEARER = "Bearer ";

  private final MockMvc mockMvc;
  private final OauthTokenProvider oauthTokenProvider;

  @Test
  @DisplayName("getting products")
  void shouldGetProducts(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());

    mockMvc
        .perform(
            get("/products/")
                .header(AUTHORIZATION, BEARER + oauthTokenProvider.getAuthToken().getAccessToken()))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
  }
}

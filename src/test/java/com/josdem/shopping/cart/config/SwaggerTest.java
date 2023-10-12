package com.josdem.shopping.cart.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class SwaggerTest {

  private final MockMvc mockMvc;

  @Test
  @DisplayName("it gets Swagger UI")
  void shouldGetSwaggerUI(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());
    mockMvc.perform(get("/webjars/swagger-ui/index.html")).andExpect(status().isOk());
  }
}

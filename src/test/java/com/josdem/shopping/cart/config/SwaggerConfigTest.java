package com.josdem.shopping.cart.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public class SwaggerConfigTest {
  private SwaggerConfig swaggerConfig;

  private static final String SWAGGER_SERVER = "http://localhost:8080";

  @BeforeEach
  void setup() {
    swaggerConfig = new SwaggerConfig(SWAGGER_SERVER);
  }

  @Test
  @DisplayName("it set Swagger server")
  void shouldSetSwaggerServer(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    assertEquals(1, swaggerConfig.customOpenAPI().getServers().size(), "should have one server");
    assertEquals(SWAGGER_SERVER, swaggerConfig.customOpenAPI().getServers().get(0).getUrl());
  }
}

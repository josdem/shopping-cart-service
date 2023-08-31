package com.josdem.shopping.cart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

  private String swaggerServer;

  public SwaggerConfig(@Value("${swagger.server}") String swaggerServer) {
    this.swaggerServer = swaggerServer;
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().servers(Arrays.asList(new Server().url(swaggerServer)));
  }
}

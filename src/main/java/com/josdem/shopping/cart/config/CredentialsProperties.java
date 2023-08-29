package com.josdem.shopping.cart.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("auth")
public class CredentialsProperties {
  private String username;
  private String password;
}

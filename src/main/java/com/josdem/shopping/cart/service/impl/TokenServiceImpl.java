package com.josdem.shopping.cart.service.impl;

import com.josdem.shopping.cart.config.ApplicationProperties;
import com.josdem.shopping.cart.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final ApplicationProperties applicationProperties;

  @Override
  public boolean isValid(String token) {
    return applicationProperties.getToken().equals(token);
  }
}

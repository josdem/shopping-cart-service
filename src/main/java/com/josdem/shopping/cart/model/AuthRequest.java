package com.josdem.shopping.cart.model;

import lombok.Data;

@Data
public class AuthRequest {
  private String username;
  private String password;
}

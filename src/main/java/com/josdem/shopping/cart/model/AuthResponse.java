package com.josdem.shopping.cart.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthResponse {

    private String token = UUID.randomUUID().toString();

}

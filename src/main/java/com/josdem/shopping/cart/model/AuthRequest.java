package com.josdem.shopping.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthRequest {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
}

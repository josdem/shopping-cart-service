package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/products")
public class ProductController {

  @GetMapping("/")
  public Flux<Product> getProducts() {
    return Flux.fromIterable(
        Arrays.asList(
            new Product("100", "Nike Air Max", new BigDecimal(1259.00)),
            new Product("101", "Adidas Air Max", new BigDecimal(1260.00))));
  }
}

package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

  private Map<String, Product> products =
      Map.of(
          "100",
          new Product("100", "Nike Air Max", new BigDecimal(1259.00)),
          "101",
          new Product("101", "Adidas Air Max", new BigDecimal(1260.00)));

  @GetMapping("/")
  public Flux<Product> getProducts() {
    return Flux.fromIterable(products.values());
  }

  @GetMapping("/{id}")
  public Mono<Product> getProductById(@PathVariable String id) {
    Optional<Product> optional = Optional.ofNullable(products.get(id));
    return Mono.just(optional.get());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleException() {
    return new ResponseEntity<String>("Product not found", HttpStatus.NOT_FOUND);
  }
}

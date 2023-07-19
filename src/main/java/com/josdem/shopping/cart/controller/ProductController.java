package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.config.ProductProperties;
import com.josdem.shopping.cart.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final Map<String, Product> products = new HashMap<>();
  private final ProductProperties productProperties;

  @PostConstruct
  void setup() {
    productProperties
        .getProducts()
        .forEach(
            product -> {
              products.put(product.getSku(), product);
            });
  }

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

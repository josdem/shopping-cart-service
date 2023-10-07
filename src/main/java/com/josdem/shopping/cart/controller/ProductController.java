package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.config.ApplicationState;
import com.josdem.shopping.cart.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ApplicationState applicationState;

  @GetMapping("/")
  public List<Product> getProducts() {
    return new ArrayList<>(applicationState.getProducts().values());
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable String id) {
    Optional<Product> optional = Optional.ofNullable(applicationState.getProducts().get(id));
    return optional.orElseThrow(NoSuchElementException::new);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleException() {
    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
  }
}

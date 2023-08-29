package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.model.Product;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ApplicationState {

  private final ProductProperties productProperties;
  private final Map<String, Product> products = new HashMap<>();

  @PostConstruct
  void setup() {
    productProperties
        .getProducts()
        .forEach(
            product -> {
              products.put(product.getSku(), product);
            });
  }
}

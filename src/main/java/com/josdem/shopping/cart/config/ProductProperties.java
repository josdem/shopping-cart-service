package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.model.Product;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("inventory")
public class ProductProperties {
  List<Product> products;
}

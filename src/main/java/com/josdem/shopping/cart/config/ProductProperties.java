package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("inventory")
public class ProductProperties {
    List<Product> products;
}

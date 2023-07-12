package com.josdem.shopping.cart.config;

import com.josdem.shopping.cart.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Component
public class ApplicationState {

    private Map<String, Product> products =
            Map.of(
                    "100",
                    new Product("100", "Nike Air Max", new BigDecimal(1259.00)),
                    "101",
                    new Product("101", "Adidas Air Max", new BigDecimal(1260.00)));
}

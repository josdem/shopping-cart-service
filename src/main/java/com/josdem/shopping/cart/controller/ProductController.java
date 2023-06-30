package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<String, Product> products = Map.of("100", new Product("100", "Nike Air Max", new BigDecimal(1259.00)), "101", new Product("101", "Adidas Air Max", new BigDecimal(1260.00)));

    @GetMapping("/")
    public Flux<Product> getProducts() {
        return Flux.fromIterable(products.values());
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return Mono.just(products.get(id));
    }
}

package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.model.Authorization;
import com.josdem.shopping.cart.model.Product;
import com.josdem.shopping.cart.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final TokenService tokenService;

    private Map<String, Product> products =
            Map.of(
                    "100",
                    new Product("100", "Nike Air Max", new BigDecimal(1259.00))
            );

    @PostMapping("/")
    public Flux<Product> getProducts(@RequestBody Authorization authorization) {
        log.info("Calling cart");
        if (!tokenService.isValid(authorization.getToken())) {
            return Flux.empty();
        }
        return Flux.fromIterable(products.values());
    }

}

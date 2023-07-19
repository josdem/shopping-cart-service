package com.josdem.shopping.cart.controller;

import com.josdem.shopping.cart.config.ApplicationState;
import com.josdem.shopping.cart.model.Authorization;
import com.josdem.shopping.cart.model.Product;
import com.josdem.shopping.cart.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final TokenService tokenService;
    private final ApplicationState applicationState;
    private final Map<String, Product> cart = new HashMap<>();

    @PostMapping("/")
    public Flux<Product> getCart(@RequestBody Authorization authorization) {
        log.info("Calling cart");
        if (!tokenService.isValid(authorization.getToken())) {
            return Flux.empty();
        }
        return Flux.fromIterable(cart.values());
    }

    @PostMapping("/{sku}")
    public Mono<HttpStatus> addProductById(
            @PathVariable String sku, @RequestBody Authorization authorization) {
        log.info("Adding to the cart");
        if (!tokenService.isValid(authorization.getToken())) {
            return Mono.just(HttpStatus.UNAUTHORIZED);
        }
        return Mono.just(applicationState.getProducts().get(sku))
                .doOnNext(product -> cart.put(product.getSku(), product))
                .map(product -> HttpStatus.OK);
    }

    @DeleteMapping("/")
    public Mono<HttpStatus> clearProducts() {
        log.info("Calling clear cart");
        cart.clear();
        return Mono.just(HttpStatus.OK);
    }
}

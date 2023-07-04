package com.josdem.shopping.cart.service;

import com.josdem.shopping.cart.model.FormattedMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MessageService {
  public Flux<FormattedMessage> getDefaultMessage() {
    return Flux.just(new FormattedMessage());
  }

  public Flux<FormattedMessage> getCustomMessage(String name) {
    return Flux.just(new FormattedMessage(name));
  }
}

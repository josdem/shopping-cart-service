package com.josdem.shopping.cart;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ShoppingCartApplicationTest {

  private final ApplicationContext applicationContext;

  @Test
  @DisplayName("loading application")
  void shouldLoadApplication(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    ShoppingCartApplication.main(new String[] {});
    assertNotNull(applicationContext, "should have spring application context");
  }
}

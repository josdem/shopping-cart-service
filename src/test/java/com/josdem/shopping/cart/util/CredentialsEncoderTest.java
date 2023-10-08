package com.josdem.shopping.cart.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class CredentialsEncoderTest {
  private static final String REG_EXP =
      "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";

  @Test
  @DisplayName("it encodes credentials")
  void shouldEncodeBase64(TestInfo testInfo) {
    log.info("Running: {}", testInfo.getDisplayName());
    assertTrue(
        CredentialsEncoder.encode("josdem", "12345678").matches(REG_EXP),
        "Should be a valid base64");
  }
}

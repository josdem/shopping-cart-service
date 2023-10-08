package com.josdem.shopping.cart.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.GRANT_TYPE;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.SCOPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.josdem.shopping.cart.model.AuthToken;
import com.josdem.shopping.cart.util.CredentialsEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ProductControllerTest {

  private final MockMvc mockMvc;

  public static final String BASIC = "Basic ";
  public static final String TEST_USERNAME = "client";
  public static final String TEST_PASSWORD = "secret";
  public static final String WRITE = "write";
  public static final String URL = "https://auth.josdem.io/oauth2/token";
  public static final String BEARER = "Bearer ";

  private final RestTemplate restTemplate = new RestTemplate();
  private final HttpHeaders httpHeaders = new HttpHeaders();

  private HttpEntity<MultiValueMap> httpEntity;
  private ResponseEntity<AuthToken> response;
  private MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

  @BeforeAll
  void setup() {
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add(AUTHORIZATION, BASIC + CredentialsEncoder.encode(TEST_USERNAME, TEST_PASSWORD));
    body.add(GRANT_TYPE, CLIENT_CREDENTIALS.getValue());
    body.add(SCOPE, WRITE);
    httpEntity = new HttpEntity<>(body, httpHeaders);
    response = restTemplate.postForEntity(URL, httpEntity, AuthToken.class);
  }

  @Test
  @DisplayName("getting products")
  void shouldGetProducts(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());

    mockMvc
        .perform(
            get("/products/").header(AUTHORIZATION, BEARER + response.getBody().getAccessToken()))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
  }
}

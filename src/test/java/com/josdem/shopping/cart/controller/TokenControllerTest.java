package com.josdem.shopping.cart.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.GRANT_TYPE;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.SCOPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josdem.shopping.cart.model.AuthRequest;
import com.josdem.shopping.cart.model.AuthToken;
import com.josdem.shopping.cart.util.CredentialsEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TokenControllerTest {

  public static final String BASIC = "Basic ";
  public static final String TEST_USERNAME = "client";
  public static final String TEST_PASSWORD = "secret";
  public static final String WRITE = "write";
  public static final String URL = "https://auth.josdem.io/oauth2/token";
  public static final String BEARER = "Bearer ";

  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;
  private final AuthRequest authRequest = new AuthRequest();
  private final HttpHeaders httpHeaders = new HttpHeaders();
  private final RestTemplate restTemplate = new RestTemplate();
  private final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
  private ResponseEntity<AuthToken> response;
  private HttpEntity<MultiValueMap> httpEntity;

  @BeforeEach
  void setup() {
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add(AUTHORIZATION, BASIC + CredentialsEncoder.encode(TEST_USERNAME, TEST_PASSWORD));
    body.add(GRANT_TYPE, CLIENT_CREDENTIALS.getValue());
    body.add(SCOPE, WRITE);
    httpEntity = new HttpEntity<>(body, httpHeaders);
    response = restTemplate.postForEntity(URL, httpEntity, AuthToken.class);
    authRequest.setUsername("josdem");
    authRequest.setPassword("12345678");
  }

  @Test
  @DisplayName("getting token")
  void shouldGetToken(TestInfo testInfo) throws Exception {
    log.info("Running: {}", testInfo.getDisplayName());

    mockMvc
        .perform(
            post("/login")
                .header(AUTHORIZATION, BEARER + response.getBody().getAccessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(status().isOk());
  }
}

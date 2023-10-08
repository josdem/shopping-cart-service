package com.josdem.shopping.cart.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.GRANT_TYPE;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.SCOPE;

import com.josdem.shopping.cart.model.AuthToken;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class OauthTokenProvider {

  public static final String BASIC = "Basic ";
  public static final String TEST_USERNAME = "client";
  public static final String TEST_PASSWORD = "secret";
  public static final String WRITE = "write";
  public static final String URL = "https://auth.josdem.io/oauth2/token";

  private final HttpHeaders httpHeaders = new HttpHeaders();
  private final RestTemplate restTemplate = new RestTemplate();
  private final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
  private ResponseEntity<AuthToken> response;
  private HttpEntity<MultiValueMap> httpEntity;

  @PostConstruct
  void setup() {
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add(AUTHORIZATION, BASIC + CredentialsEncoder.encode(TEST_USERNAME, TEST_PASSWORD));
    body.add(GRANT_TYPE, CLIENT_CREDENTIALS.getValue());
    body.add(SCOPE, WRITE);
    httpEntity = new HttpEntity<>(body, httpHeaders);
    response = restTemplate.postForEntity(URL, httpEntity, AuthToken.class);
  }

  public AuthToken getAuthToken() {
    return response.getBody();
  }
}

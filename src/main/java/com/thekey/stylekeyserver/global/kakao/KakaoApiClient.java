package com.thekey.stylekeyserver.global.kakao;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;
import com.thekey.stylekeyserver.auth.domain.oauth.OAuthApiClient;
import com.thekey.stylekeyserver.auth.domain.oauth.OAuthInfoResponse;
import com.thekey.stylekeyserver.auth.domain.oauth.OAuthLoginParams;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String authUrl;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String apiUrl;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    private final RestTemplate restTemplate;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public String requestAccessToken(OAuthLoginParams params) {
        String url = authUrl; // + "/oauth/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        KakaoTokens response = restTemplate.postForObject(url, request, KakaoTokens.class);

        assert response != null;
        return response.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl; // + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.account_email\", \"kakao_account.profile_nickname\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }
}

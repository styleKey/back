package com.thekey.stylekeyserver.auth.domain.oauth;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}

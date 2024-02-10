package com.thekey.stylekeyserver.auth.domain.oauth;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}

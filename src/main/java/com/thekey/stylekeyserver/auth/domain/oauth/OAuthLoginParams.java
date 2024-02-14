package com.thekey.stylekeyserver.auth.domain.oauth;

import org.springframework.util.MultiValueMap;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}

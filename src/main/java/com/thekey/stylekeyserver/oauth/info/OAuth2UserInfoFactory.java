package com.thekey.stylekeyserver.oauth.info;

import java.util.Map;

import com.thekey.stylekeyserver.oauth.entity.ProviderType;
import com.thekey.stylekeyserver.oauth.info.impl.GoogleOAuth2UserInfo;
import com.thekey.stylekeyserver.oauth.info.impl.KakaoOAuth2UserInfo;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}

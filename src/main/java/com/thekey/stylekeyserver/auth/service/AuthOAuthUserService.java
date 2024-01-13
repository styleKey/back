package com.thekey.stylekeyserver.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.domain.OAuthAuthenticatedUser;
import com.thekey.stylekeyserver.auth.domain.OAuthProviderEnum;
import com.thekey.stylekeyserver.auth.entity.AuthOAuthEntity;
import com.thekey.stylekeyserver.auth.entity.AuthOAuthKey;
import com.thekey.stylekeyserver.auth.repository.AuthOAuthRepository;

@Service
public class AuthOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AuthOAuthRepository authOAuthJpaRepository;
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    public AuthOAuthUserService(AuthOAuthRepository authOAuthJpaRepository) {
        this.authOAuthJpaRepository = authOAuthJpaRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String providerId = userRequest.getClientRegistration().getRegistrationId();

        String id = oAuth2User.getName();
        OAuthProviderEnum provider = OAuthProviderEnum.valueOf(providerId.toUpperCase());

        AuthOAuthKey authOAuthKey = new AuthOAuthKey(provider, id);

        return authOAuthJpaRepository.findById(authOAuthKey)
                .map(authEntity -> new OAuthAuthenticatedUser(oAuth2User, AuthorityUtils.NO_AUTHORITIES, authEntity.getUserId()))
                .orElseGet(() -> {
                    AuthOAuthEntity newAuthEntity = new AuthOAuthEntity(authOAuthKey, null, null);
                    authOAuthJpaRepository.save(newAuthEntity);
                    return new OAuthAuthenticatedUser(oAuth2User, AuthorityUtils.NO_AUTHORITIES, newAuthEntity.getUserId());
                });
    }

}

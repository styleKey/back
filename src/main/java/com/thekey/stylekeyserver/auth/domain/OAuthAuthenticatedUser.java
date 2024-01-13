package com.thekey.stylekeyserver.auth.domain;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class OAuthAuthenticatedUser implements OAuth2User, AuthenticatedUser {
    private final OAuth2User oAuth2User;
    private final Collection<GrantedAuthority> authorities;
    private final UUID userId;

    public OAuthAuthenticatedUser(OAuth2User oAuth2User, Collection<GrantedAuthority> authorities, UUID userId) {
        this.oAuth2User = oAuth2User;
        this.authorities = authorities;
        this.userId = userId;
    }

    @Override
    public String getName() {
        return this.oAuth2User.getName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.oAuth2User.getAttributes();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public UUID getUserId() {
        return this.userId;
    }
}

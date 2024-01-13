package com.thekey.stylekeyserver.auth.entity;

import java.io.Serializable;

import com.thekey.stylekeyserver.auth.domain.OAuthProviderEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class AuthOAuthKey implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_provider")
    private OAuthProviderEnum oAuthProvider;

    @Column(name = "id")
    private String id;


    public AuthOAuthKey() {
    }

    public AuthOAuthKey(OAuthProviderEnum oAuthProvider, String id) {
        this.oAuthProvider = oAuthProvider;
        this.id = id;
    }

    public OAuthProviderEnum getOAuthProvider() {
        return oAuthProvider;
    }

    public void setOAuthProvider(OAuthProviderEnum oAuthProvider) {
        this.oAuthProvider = oAuthProvider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


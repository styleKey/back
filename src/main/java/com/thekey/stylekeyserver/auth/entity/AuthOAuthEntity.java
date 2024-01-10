package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_oauth")
public class AuthOAuthEntity extends AuthEntity {
    @EmbeddedId
    private AuthOAuthKey key;

    public AuthOAuthEntity(AuthOAuthKey key, UUID userId, OffsetDateTime createdAt) {
        super(userId, createdAt); 
        this.key = key;
    }

    public AuthOAuthKey getKey() {
        return key;
    }

    public void setKey(AuthOAuthKey key) {
        this.key = key;
    }
}


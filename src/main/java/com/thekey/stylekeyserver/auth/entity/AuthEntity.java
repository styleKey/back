package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@MappedSuperclass
public abstract class AuthEntity {
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();
 
    public AuthEntity(UUID userId, OffsetDateTime createdAt) {
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}

package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuthEntity {
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    // 기본 생성자 추가
    public AuthEntity() {
    }

    // 매개변수를 받는 생성자 추가 -> 다른 엔티티 파일 super undefined 오류 해결 
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

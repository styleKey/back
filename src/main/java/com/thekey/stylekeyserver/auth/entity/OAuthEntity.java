package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class OAuthEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable = false)
    private String resourceServeId;

    public static OAuthEntity of(String email, OffsetDateTime createdAt, String resourceServeId){
        return OAuthEntity.builder()
                .email(email)
                .createdAt(createdAt)
                .resourceServeId(resourceServeId)
                .build();
     }
}

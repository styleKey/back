package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable = false)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    public static AuthEntity of(String userId, String password, OffsetDateTime createdAt){
        return AuthEntity.builder()
                .userId(userId)
                .password(password)
                .createdAt(createdAt)
                .build();
     }
  
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthEntity that = (AuthEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

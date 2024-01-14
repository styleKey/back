package com.thekey.stylekeyserver.auth.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_password")
public class AuthPasswordEntity extends AuthEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    public AuthPasswordEntity(String id, String encryptedPassword, UUID userId, OffsetDateTime createdAt) {
        super(userId, createdAt);
        this.id = id;
        this.encryptedPassword = encryptedPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}

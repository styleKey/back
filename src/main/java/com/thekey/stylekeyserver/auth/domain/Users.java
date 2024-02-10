package com.thekey.stylekeyserver.auth.domain;

import com.thekey.stylekeyserver.auth.domain.enums.OAuthProvider;
import com.thekey.stylekeyserver.auth.domain.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private OAuthProvider oAuthProvider;

    private String provider;

    @Builder
    public Users(Long id, String name, String email, Role role, OAuthProvider oAuthProvider, String provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.oAuthProvider = oAuthProvider;
        this.provider = provider; 
    }

    public Users update(String name, String provider) {
        this.name = name;
        this.provider = provider;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
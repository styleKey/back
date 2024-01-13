package com.thekey.stylekeyserver.auth.domain;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class PasswordAuthenticatedUser extends User implements AuthenticatedUser {
    private final UUID userId;

    public PasswordAuthenticatedUser(String id, String password, Collection<GrantedAuthority> authorities, UUID userId) {
        super(id, password, authorities);
        this.userId = userId;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }
}

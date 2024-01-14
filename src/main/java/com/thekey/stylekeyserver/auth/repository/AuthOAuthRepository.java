package com.thekey.stylekeyserver.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thekey.stylekeyserver.auth.entity.AuthOAuthEntity;
import com.thekey.stylekeyserver.auth.entity.AuthOAuthKey;

public interface AuthOAuthRepository extends JpaRepository<AuthOAuthEntity, AuthOAuthKey> {
}

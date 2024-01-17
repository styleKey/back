package com.thekey.stylekeyserver.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thekey.stylekeyserver.auth.entity.AuthEntity;

@Repository
public interface AuthRepository extends JpaRepository <AuthEntity,Long> {

    Optional<AuthEntity> findByUserId(String userId);
}

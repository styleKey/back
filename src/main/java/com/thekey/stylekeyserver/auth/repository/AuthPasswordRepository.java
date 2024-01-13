package com.thekey.stylekeyserver.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thekey.stylekeyserver.auth.entity.AuthPasswordEntity;

public interface AuthPasswordRepository extends JpaRepository<AuthPasswordEntity, String> {
}

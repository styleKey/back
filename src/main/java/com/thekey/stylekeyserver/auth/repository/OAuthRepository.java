package com.thekey.stylekeyserver.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thekey.stylekeyserver.auth.entity.OAuthEntity;


@Repository
public interface OAuthRepository extends JpaRepository <OAuthRepository,Long> {

    Optional<OAuthEntity> findByResourceServeId(String resourceServeId);
}

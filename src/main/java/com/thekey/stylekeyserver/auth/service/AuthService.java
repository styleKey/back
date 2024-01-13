package com.thekey.stylekeyserver.auth.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.AuthErrorMessage;
import com.thekey.stylekeyserver.auth.entity.AuthPasswordEntity;
import com.thekey.stylekeyserver.auth.repository.AuthPasswordRepository;
import com.thekey.stylekeyserver.auth.service.dto.RegisterPasswordAuthRequest;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
    private final AuthPasswordRepository authPasswordJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthPasswordRepository authPasswordJpaRepository, PasswordEncoder passwordEncoder) {
        this.authPasswordJpaRepository = authPasswordJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerPasswordAuth(RegisterPasswordAuthRequest request) {
        AuthPasswordEntity authEntity = authPasswordJpaRepository.findById(request.getId()).orElse(null);

        if (authEntity != null) {
            throw new DataIntegrityViolationException(AuthErrorMessage.ALREADY_REGISTERED.get());
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        AuthPasswordEntity newAuthEntity = new AuthPasswordEntity(request.getId(), encryptedPassword, null, null);
        authPasswordJpaRepository.save(newAuthEntity);
    }
}
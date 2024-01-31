package com.thekey.stylekeyserver.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.dto.request.OAuthRequestDto;
import com.thekey.stylekeyserver.auth.dto.response.AuthResponseDto;
import com.thekey.stylekeyserver.auth.repository.AuthRepository;
import com.thekey.stylekeyserver.auth.repository.OAuthRepository;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;
import com.thekey.stylekeyserver.global.response.SuccessType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final OAuthRepository oAuthRepository;
    private final AuthRepository authRepository;

    @Transactional
    public ResponseEntity <ApiResponseDto> oauthLogin(String provider, OAuthRequestDto oAuthRequestDto) {

        AuthResponseDto authResponseDto = null;
        return ResponseEntity.ok(ApiResponseDto.of(SuccessType.LOG_IN_SUCCESS, authResponseDto));
    }
}

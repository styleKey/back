package com.thekey.stylekeyserver.auth.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thekey.stylekeyserver.auth.AuthErrorMessage;
import com.thekey.stylekeyserver.auth.dto.request.AuthRequestDto;
import com.thekey.stylekeyserver.auth.dto.response.AuthResponseDto;
import com.thekey.stylekeyserver.auth.entity.AuthEntity;
import com.thekey.stylekeyserver.auth.repository.AuthRepository;
import com.thekey.stylekeyserver.global.jwt.JwtUtil;
import com.thekey.stylekeyserver.global.jwt.TokenDto;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;
import com.thekey.stylekeyserver.global.response.ErrorType;
import com.thekey.stylekeyserver.global.response.SuccessType;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<ApiResponseDto> signUp(AuthRequestDto authRequestDto) {

        Optional<AuthEntity> member = authRepository.findByUserId(authRequestDto.getMemberId());

        if (member.isPresent()) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.USER_EXIST));
        }

        String encodedPassword = passwordEncoder.encode(authRequestDto.getPassword());
        OffsetDateTime now = OffsetDateTime.now();
        authRepository.save(AuthEntity.of(authRequestDto.getMemberId(), encodedPassword, now));
        return ResponseEntity.ok(ApiResponseDto.of(SuccessType.SIGN_UP_SUCCESS));
    }

    public ResponseEntity<ApiResponseDto> login(AuthRequestDto authRequestDto, HttpServletResponse response) {

        Optional<AuthEntity> member = authRepository.findByUserId(authRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.USER_NOT_FOUND));
        }

        if (!passwordEncoder.matches(authRequestDto.getPassword(), member.get().getPassword())) {
            return ResponseEntity.ok(ApiResponseDto.of(ErrorType.PASSWORD_MISMATCH));
        }

        TokenDto tokenDto = new TokenDto(jwtUtil.createToken(authRequestDto.getMemberId()));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, tokenDto.getAccessToken());

        return ResponseEntity.ok(ApiResponseDto.of(SuccessType.LOG_IN_SUCCESS,
                AuthResponseDto.of(member.get().getUserId())));
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> changePassword(AuthRequestDto authRequestDto) {

        Optional<AuthEntity> member = authRepository.findByUserId(authRequestDto.getMemberId());

        if (member.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponseDto.of(ErrorType.USER_NOT_FOUND));
        }

        String encodedPassword = passwordEncoder.encode(authRequestDto.getPassword());
        member.get().changePassword(encodedPassword);
        authRepository.save(member.get());

        return ResponseEntity.ok(ApiResponseDto.of(SuccessType.CHANGE_PASSWORD));
    }
}
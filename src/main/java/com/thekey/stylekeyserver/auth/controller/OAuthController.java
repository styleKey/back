package com.thekey.stylekeyserver.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thekey.stylekeyserver.auth.dto.request.KakaoLoginParams;
import com.thekey.stylekeyserver.auth.dto.response.OAuthTokens;
import com.thekey.stylekeyserver.auth.service.OAuthLoginService;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "OAuth", description = "OAuth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    @Operation(summary = "kakao api", description = "kakao code 발급 이후 token 반환 API입니다.")
    public ResponseEntity<OAuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    
}

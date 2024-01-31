package com.thekey.stylekeyserver.auth.controller;

import org.springframework.web.bind.annotation.RestController;

import com.thekey.stylekeyserver.auth.dto.request.OAuthRequestDto;
import com.thekey.stylekeyserver.auth.service.OAuthService;
import com.thekey.stylekeyserver.global.response.ApiResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "OAuth", description = "OAuth API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("/oauth/{provider}/login")
    public ResponseEntity <ApiResponseDto> oauthLogin(@PathVariable String provider, @RequestBody OAuthRequestDto oAuthRequestDto) {
        return oAuthService.oauthLogin(provider, oAuthRequestDto);
    }
    
}

package com.thekey.stylekeyserver.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class AuthReissueResponseDto {
    private final String access_token;
    
    public static AuthReissueResponseDto of(String access_token) {
        return AuthReissueResponseDto.builder()
                .access_token(access_token)
                .build();
    }
}

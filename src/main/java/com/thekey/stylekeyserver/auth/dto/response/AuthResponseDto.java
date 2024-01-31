package com.thekey.stylekeyserver.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDto {
    private String member_id;
    private final String access_token;
    private final String refresh_token;

    public static AuthResponseDto of(String member_id, String access_token, String refresh_token) {
        return AuthResponseDto.builder()
                .member_id(member_id)
                .access_token(access_token)
                .refresh_token(refresh_token)
                .build();
    }
}

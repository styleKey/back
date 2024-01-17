package com.thekey.stylekeyserver.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDto {
    private String memberId;

    public static AuthResponseDto of(String memberId) {
        return AuthResponseDto.builder()
                .memberId(memberId)
                .build();
    }
}

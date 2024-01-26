package com.thekey.stylekeyserver.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDto {
    private String member_id;

    public static AuthResponseDto of(String member_id) {
        return AuthResponseDto.builder()
                .member_id(member_id)
                .build();
    }
}

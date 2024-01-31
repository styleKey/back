package com.thekey.stylekeyserver.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthReissueResponseDto {
    private final String access_token;
    private final String refresh_token;
    
    public static AuthReissueResponseDtoBuilder builder() {
        return new AuthReissueResponseDtoBuilder();
    }

    public static class AuthReissueResponseDtoBuilder {
        private String access_token;
        private String refresh_token;

        AuthReissueResponseDtoBuilder() {
        }

        public AuthReissueResponseDtoBuilder access_token(String access_token) {
            this.access_token = access_token;
            return this;
        }

        public AuthReissueResponseDtoBuilder refresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
            return this;
        }

        public AuthReissueResponseDto build() {
            return new AuthReissueResponseDto(this.access_token, this.refresh_token);
        }
    }
}
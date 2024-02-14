package com.thekey.stylekeyserver.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthTokens {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;

    public static OAuthTokens of(String accessToken, String refreshToken, String grantType, Long expiresIn) {
        return new OAuthTokens(accessToken, refreshToken, grantType, expiresIn);
    }
}

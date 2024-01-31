package com.thekey.stylekeyserver.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthReissueRequestDto {
    @NotEmpty
    private String access_token;

    @NotEmpty
    private String refresh_token;

}

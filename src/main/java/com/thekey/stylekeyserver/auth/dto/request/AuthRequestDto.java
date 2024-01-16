package com.thekey.stylekeyserver.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthRequestDto {
     @NotBlank(message = "ID를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}

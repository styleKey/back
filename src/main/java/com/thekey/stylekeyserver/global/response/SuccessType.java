package com.thekey.stylekeyserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessType {


    //User 관련
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다."),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    CHANGE_PASSWORD(200, "비밀번호가 변경되었습니다."),
    USER_EXIST(200, "존재하는 회원입니다."),
    SEND_TEMPORARY_PASSWORD(200, "임시 비밀번호를 발급했습니다. 메일을 확인해주세요.");


    private final int statusCode;
    private final String message;
}
package com.thekey.stylekeyserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessType {

    /* 기본 요청 성공 메시지 */
    SUCCESS(200, "API 요청이 성공했습니다."),

    /* 로그인 관련 성공 반환 메시지 */
    SIGN_UP_SUCCESS(200, "회원 가입에 성공했습니다."),
    LOG_IN_SUCCESS(200, "로그인되었습니다."),
    CHANGE_PASSWORD(200, "비밀번호가 변경되었습니다."),
    USER_EXIST(200, "존재하는 회원입니다."),
    REISSUE_SUCCESS(200, "토큰 재발급이 성공적으로 되었습니다.");

    private final int statusCode;
    private final String message;
}
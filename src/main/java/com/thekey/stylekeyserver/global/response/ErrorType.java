package com.thekey.stylekeyserver.global.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    /* 기본 요청 에러 메시지 */
    BAD_REQUEST(201, "불가능한 요청입니다."),

    /* 로그인 관련 에러 메시지 */
    NOT_VALID_REQUEST(400,  "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(400,"유효한 토큰이 아닙니다."),
    TOKEN_NOT_FOUND(400,  "토큰이 없습니다."),

    USER_EXIST(400, "이미 존재하는 회원입니다."),
    USER_NICKNAME_EXIST(400, "이미 존재하는 닉네임입니다."),
    USER_ACCOUNT_NOT_EXIST(400,  "계정 정보가 존재하지 않습니다."),
    USER_NOT_FOUND(400, "사용자가 존재하지 않습니다."),
    PASSWORD_MISMATCH(400,  "비밀번호가 일치하지 않습니다."),

    /* 패션 컨텐츠 관련 에러 메시지 */
    FILE_UPLOAD_FAILED(400, "이미지 등록이 실패하였습니다."),
    FILE_ALREADY_EXISTS(400, "이미 등록된 이미지입니다."),
    INVALID_IMAGE_FORMAT(400, "올바른 형식의 이미지가 아닙니다.");

    private final int statusCode;
    private final String message;
}


package com.thekey.stylekeyserver.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 기본 */
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생하였습니다."),

    /* 로그인 관련 */
    NOT_VALID_REQUEST(400, "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(400, "유효하지 않은 토큰입니다."),
    INVALID_ACCESS_TOKEN(401, "잘못된 access 토큰입니다."),
    INVALID_REFRESH_TOKEN(401, "잘못된 refresh 토큰입니다."),
    NOT_EXPIRED_TOKEN_YET(403, "토큰이 아직 만료되지 않았습니다"),
    TOKEN_NOT_FOUND(400, "토큰이 없습니다."),

    USER_EXIST(400, "이미 존재하는 회원입니다."),
    USER_NICKNAME_EXIST(400, "이미 존재하는 닉네임입니다."),
    USER_ACCOUNT_NOT_EXIST(400, "계정 정보가 존재하지 않습니다."),
    USER_NOT_FOUND(400, "사용자가 존재하지 않습니다."),

    /* 패션 콘텐츠 관리 관련 */
    FILE_UPLOAD_FAILED(400, "이미지 등록이 실패하였습니다."),
    FAIL_FILE_UPDATE(400, "이미지 수정이 실패하였습니다."),
    FAIL_FILE_DELETE(400, "이미지 삭제가 실패하였습니다."),
    FILE_ALREADY_EXISTS(400, "이미 등록된 이미지입니다."),
    INVALID_IMAGE_FORMAT(400, "이미지 형식이 올바르지 않습니다.");

    private final int statusCode;
    private final String message;
}
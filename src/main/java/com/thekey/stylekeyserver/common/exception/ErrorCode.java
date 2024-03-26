package com.thekey.stylekeyserver.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 기본 */
    ERROR_BAD_REQUEST(BAD_REQUEST, "잘못된 요청입니다."),
    ERROR_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생하였습니다."),

    /* 로그인 관련 */
    NOT_VALID_REQUEST(BAD_REQUEST, "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(BAD_REQUEST, "유효하지 않은 토큰입니다."),
    INVALID_ACCESS_TOKEN(UNAUTHORIZED, "잘못된 access 토큰입니다."),
    INVALID_REFRESH_TOKEN(UNAUTHORIZED, "잘못된 refresh 토큰입니다."),
    NOT_EXPIRED_TOKEN_YET(FORBIDDEN, "토큰이 아직 만료되지 않았습니다"),
    TOKEN_NOT_FOUND(BAD_REQUEST, "토큰이 없습니다."),

    USER_EXIST(BAD_REQUEST, "이미 존재하는 회원입니다."),
    USER_NICKNAME_EXIST(BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    USER_ACCOUNT_NOT_EXIST(NOT_FOUND, "계정 정보가 존재하지 않습니다."),
    USER_NOT_FOUND(NOT_FOUND, "사용자가 존재하지 않습니다."),

    /* 인증 관련 */
    AUTHENTICATED_FAIL(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    AUTHORIZED_FAIL(HttpStatus.FORBIDDEN, "인가 권한이 없는 사용자입니다."),

    /* 패션 콘텐츠 관리 관련 */
    FILE_UPLOAD_FAILED(BAD_REQUEST, "이미지 등록이 실패하였습니다."),
    FAIL_FILE_UPDATE(BAD_REQUEST, "이미지 수정이 실패하였습니다."),
    FAIL_FILE_DELETE(BAD_REQUEST, "이미지 삭제가 실패하였습니다."),
    FILE_ALREADY_EXISTS(BAD_REQUEST, "이미 등록된 이미지입니다."),
    FILE_NOT_FOUND(NOT_FOUND, "요청하신 이미지가 존재하지 않습니다."),

    /* 스타일 포인트 관련 */
    STYLE_POINT_NOT_FOUND(NOT_FOUND, "해당 스타일 포인트를 찾을 수 없습니다."),

    /* 브랜드 관련*/
    BRAND_NOT_FOUND(NOT_FOUND, "해당 브랜드를 찾을 수 없습니다."),

    /* 코디룩 관련*/
    COORDINATE_LOOK_NOT_FOUND(NOT_FOUND, "해당 코디룩을 찾을 수 없습니다."),

    /* 아이템 관련*/
    ITEM_NOT_FOUND(NOT_FOUND, "해당 아이템을 찾을 수 없습니다."),

    /* 카데고리 관련*/
    CATEGORY_NOT_FOUND(NOT_FOUND, "해당 카테고리를 찾을 수 없습니다."),

    /* 테스트 관련 */
    TEST_RESULT_NOT_FOUND(NOT_FOUND, "테스트 결과를 찾을 수 없습니다."),
    TEST_ANSWER_NOT_FOUND(NOT_FOUND, "테스트 선택지를 찾을 수 없습니다."),
    UNAUTHORIZED_TEST_RESULT(FORBIDDEN, "테스트 결과에 대한 권한이 없습니다."),
    DUPLICATE_TEST_ANSWERS(BAD_REQUEST, "하나의 질문에는 하나의 답변만 할 수 있습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
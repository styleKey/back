package com.thekey.stylekeyserver.test;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TestErrorMessage {

    TEST_RESULT_NOT_FOUND(HttpStatus.NOT_FOUND, "테스트 결과를 찾을 수 없습니다."),
    TEST_ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "테스트 선택지를 찾을 수 없습니다."),
    UNAUTHORIZED_TEST_RESULT(HttpStatus.FORBIDDEN, "테스트 결과에 대한 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    TestErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

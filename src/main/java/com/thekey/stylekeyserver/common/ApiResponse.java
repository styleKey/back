package com.thekey.stylekeyserver.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    private final static String INVALID_ACCESS_TOKEN = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET = "Not expired token yet.";

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus, httpStatus.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(HttpStatus.OK, "SUCCESS", data);
    }

    public static <T> ApiResponse<T> fail() {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 오류가 발생하였습니다.", null);
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return of(HttpStatus.UNAUTHORIZED, "Invalid access token.", null);
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return of(HttpStatus.UNAUTHORIZED, "Invalid refresh token.", null);
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return of(HttpStatus.BAD_REQUEST, "Not expired token yet.", null);
    }
}

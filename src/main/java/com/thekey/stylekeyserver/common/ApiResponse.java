package com.thekey.stylekeyserver.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return of(httpStatus, httpStatus.getReasonPhrase(), data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(HttpStatus.OK, SuccessCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return new ApiResponse<>(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_ACCESS_TOKEN.getMessage());
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return new ApiResponse<>(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_REFRESH_TOKEN.getMessage());
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXPIRED_TOKEN_YET.getMessage());
    }
}

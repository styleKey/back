package com.thekey.stylekeyserver.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    private ApiResponse(HttpStatus status, String message, T data) {
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

    public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message) {
        return new ApiResponse<>(status, message);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return of(HttpStatus.OK, SuccessCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> ok() {
        return of(HttpStatus.OK, SuccessCode.SUCCESS.getMessage());
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, String message) {
        return of(status, message);
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return of(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_ACCESS_TOKEN.getMessage());
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return of(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_REFRESH_TOKEN.getMessage());
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return of(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXPIRED_TOKEN_YET.getMessage());
    }
}

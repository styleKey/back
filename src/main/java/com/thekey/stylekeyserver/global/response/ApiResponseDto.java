package com.thekey.stylekeyserver.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int statusCode;
    private String message;

    @JsonInclude(Include.NON_NULL)
    private T data;

    public static <T> ApiResponseDto of(ErrorType errorType, T data) {
        return ApiResponseDto.<T>builder()
                .statusCode(errorType.getStatusCode())
                .message(errorType.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto of(SuccessType successType, T data) {
        return ApiResponseDto.<T>builder()
                .statusCode(successType.getStatusCode())
                .message(successType.getMessage())
                .data(data)
                .build();
    }


    public static ApiResponseDto of(ErrorType errorType) {
        return ApiResponseDto.builder()
                .statusCode(errorType.getStatusCode())
                .message(errorType.getMessage())
                .build();
    }

    public static ApiResponseDto of(SuccessType successType) {
        return ApiResponseDto.builder()
                .statusCode(successType.getStatusCode())
                .message(successType.getMessage())
                .build();
    }
}


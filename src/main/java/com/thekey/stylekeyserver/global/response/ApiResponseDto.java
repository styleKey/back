package com.thekey.stylekeyserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
    private int statusCode;
    private String message;
    private Object data;

    public static ApiResponseDto of(ErrorType errorType, Object data) {
        return ApiResponseDto.builder()
                .statusCode(errorType.getStatusCode())
                .message(errorType.getMessage())
                .data(data)
                .build();
    }

    public static ApiResponseDto of(SuccessType successType, Object data) {
        return ApiResponseDto.builder()
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


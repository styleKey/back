package com.thekey.stylekeyserver.common.dto;

public class ApiResponse<T> {
    private final String code;
    private final String message;
    private final T data;

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResponse<Void> ofSuccess() {
        return ofSuccess(null);
    }

    public static <T> ApiResponse<T> ofSuccess(T data) {
        return new ApiResponse<>("", "", data);
    }

    public static ApiResponse<Void> ofFailure(String string) {
        return null;
    }
}

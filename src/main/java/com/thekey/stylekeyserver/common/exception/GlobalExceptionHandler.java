package com.thekey.stylekeyserver.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<Object> handleApiException(ApiException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Object> handleHttpMessageNotReadableException() {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, "요청 하신 바디의 형태가 잘못 되었습니다.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, "'%s'의 타입이 잘못되었습니다.",
                exception.getParameter().getParameterName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        FieldError fieldError = getFirstFieldError(exception);

        return ApiResponse.of(HttpStatus.BAD_REQUEST,
                String.format(String.format("[%s] %s", fieldError.getField(), fieldError.getDefaultMessage())));
    }

    private FieldError getFirstFieldError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return bindingResult.getFieldErrors().get(0);
    }
}

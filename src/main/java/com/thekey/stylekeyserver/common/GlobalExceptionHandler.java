package com.thekey.stylekeyserver.common;

import com.amazonaws.AmazonServiceException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ApiResponse handleIOException(IOException e) {
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler({AmazonServiceException.class, UnsupportedEncodingException.class, MalformedURLException.class, FileAlreadyExistsException.class})
    public ApiResponse handleAmazonServiceException(Exception e) {
        return ApiResponse.of(HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
    }

    @ExceptionHandler({UnsupportedEncodingException.class, MalformedURLException.class, FileAlreadyExistsException.class})
    public ApiResponse handleS3Exception(Exception e) {
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ApiResponse handleRuntimeException(IOException e) {
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

}
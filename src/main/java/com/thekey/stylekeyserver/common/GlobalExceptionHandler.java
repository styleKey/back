package com.thekey.stylekeyserver.common;

import com.amazonaws.AmazonServiceException;
import com.thekey.stylekeyserver.s3.S3ErrorMessage;
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
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INVALID_IMAGE_FORMAT.getMessage());
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ApiResponse handleFileAlreadyExistsException(FileAlreadyExistsException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, S3ErrorMessage.FILE_ALREADY_EXISTS.getMessage());
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ApiResponse handleAmazonServiceException(Exception e) {
        return ApiResponse.of(HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
    }

    @ExceptionHandler({UnsupportedEncodingException.class, MalformedURLException.class})
    public ApiResponse handleS3Exception(Exception e) {
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleRuntimeException(RuntimeException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }
}

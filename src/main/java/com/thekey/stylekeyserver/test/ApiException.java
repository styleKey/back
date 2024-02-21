package com.thekey.stylekeyserver.test;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final TestErrorMessage errorCode;

    public ApiException(TestErrorMessage errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

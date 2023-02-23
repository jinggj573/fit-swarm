package com.support.fit.common.exception;

public class ValidateCodeException extends RuntimeException{
    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

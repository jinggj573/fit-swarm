package com.support.fit.common.exception;

import com.support.fit.common.api.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode
public class SystemException extends RuntimeException {
    private long errorCode;

    private String errorMsg;

    public SystemException() {
        this.errorCode = ResultCode.FAILED.getCode();
        this.errorMsg = ResultCode.FAILED.getMessage();
    }

    public SystemException(String message) {
        this.errorCode = ResultCode.FAILED.getCode();;
        this.errorMsg = message;
    }

    public SystemException(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}

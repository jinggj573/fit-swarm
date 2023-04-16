package com.support.fit.common.core.exception;

import com.support.fit.common.core.api.BaseErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BizException extends RuntimeException {
    private BaseErrorCode iErrorCode;

    private long errorCode;

    private String errorMsg;

    public BaseErrorCode getiErrorCode() {
        return iErrorCode;
    }

    public void setiErrorCode(BaseErrorCode iErrorCode) {
        this.iErrorCode = iErrorCode;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

package com.support.fit.common.exception;

import com.support.fit.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = {Exception.class})
    public CommonResult handlerException(Exception exception, HttpServletRequest request) {
        log.error("请求路径uri={},系统内部出现异常:{}", request.getRequestURI(), exception);
        return CommonResult.failed(exception.toString());
    }


    @ExceptionHandler(value = BizException.class)
    public CommonResult handle(BizException e) {
        if (e.getiErrorCode() !=null) {
            return CommonResult.failed(e.getiErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }


    /**
     * 自定义系统异常-SystemException
     */
    @ExceptionHandler(value = {SystemException.class})
    public CommonResult handlerCustomException(SystemException exception) {
        return CommonResult.failed(exception.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

}

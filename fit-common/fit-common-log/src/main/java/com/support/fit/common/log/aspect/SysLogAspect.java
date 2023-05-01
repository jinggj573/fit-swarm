package com.support.fit.common.log.aspect;

import cn.hutool.core.util.StrUtil;
import com.support.fit.common.core.util.LogTypeEnum;
import com.support.fit.common.core.util.SpringContextHolder;
import com.support.fit.common.log.event.SysLogEvent;
import com.support.fit.common.log.util.SysLogUtils;
import com.support.fit.mbg.model.SysLog;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;

/**
 * 操作日志使用spring event异步入库
 */
@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, com.support.fit.common.log.annotation.SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        String value = sysLog.value();
        String expression = sysLog.expression();
        if (StrUtil.isNotBlank(expression)) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            EvaluationContext context = SysLogUtils.getContext(point.getArgs(), signature.getMethod());
            try {
                value = SysLogUtils.getValue(context, expression, String.class);
            }catch (Exception e) {
                log.error("@SysLog 解析SPEL {} 异常", expression);
            }
        }

        SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(value);
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj;

        try {
            obj = point.proceed();
        } catch (Exception e) {
            logVo.setType(LogTypeEnum.ERROR.getType());
            logVo.setException(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logVo.setTime(endTime - startTime);
            SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        }
        return obj;
    }
}

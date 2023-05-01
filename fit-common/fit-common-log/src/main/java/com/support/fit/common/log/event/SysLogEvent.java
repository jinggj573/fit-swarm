package com.support.fit.common.log.event;

import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(Object source) {
        super(source);
    }
}

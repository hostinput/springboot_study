package com.example.springboot.system.entity;

import org.springframework.context.ApplicationEvent;

public class SystemLogEvent extends ApplicationEvent {
    private OperationalLog operationalLog;
    public SystemLogEvent(OperationalLog operationalLog) {
        super(operationalLog);
        this.operationalLog=operationalLog;
    }

    public OperationalLog getOperationalLog() {
        return operationalLog;
    }

    public void setOperationalLog(OperationalLog operationalLog) {
        this.operationalLog = operationalLog;
    }
}

package com.example.springboot.system.listener;

import com.example.springboot.system.entity.OperationalLog;
import com.example.springboot.system.entity.SystemLogEvent;
import com.example.springboot.system.service.IOperationalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OperationListener {
    @Autowired
    IOperationalLogService operationalLogServiceImpl;

    /**
     * 用户操作日志异步保存到数据库
     */
    @EventListener
    @Transactional
    public void handEvent(SystemLogEvent event) {
        OperationalLog log = event.getOperationalLog();
        operationalLogServiceImpl.insert(log);

    }
}

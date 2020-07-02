package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.OperationalLog;
import com.example.springboot.system.mapper.OperationalLogMapper;
import com.example.springboot.system.service.IOperationalLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
@Service
public class OperationalLogServiceImpl extends ServiceImpl<OperationalLogMapper, OperationalLog> implements IOperationalLogService {

}

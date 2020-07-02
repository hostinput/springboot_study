package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.Permission;
import com.example.springboot.system.mapper.PermissionMapper;
import com.example.springboot.system.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

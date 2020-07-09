package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.Role;
import com.example.springboot.system.mapper.RoleMapper;
import com.example.springboot.system.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-07-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

package com.example.springboot.system.service.impl;

import com.example.springboot.system.entity.Permission;
import com.example.springboot.system.entity.Role;
import com.example.springboot.system.entity.User;
import com.example.springboot.system.mapper.UserMapper;
import com.example.springboot.system.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapperImpl;

    @Override
    public List<Role> getRoleListByUserId(Long userId) {
        return userMapperImpl.selectRoleListByUserId(userId);
    }

    @Override
    public List<Permission> getPermissionListByRoleId(Long roleId) {
        return userMapperImpl.selectPermissionListByRoleId(roleId);
    }
}

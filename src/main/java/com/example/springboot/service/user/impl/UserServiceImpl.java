package com.example.springboot.service.user.impl;

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.model.Permission;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Integer register(User user) {
        Integer userId = userMapper.save(user);
        return userId;
    }

    @Override
    public User findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userMapper.getUserByLoginName(loginName);
    }

    @Override
    public List<Role> getRoleListByUserId(Long userId) {
        return userMapper.getRoleListByUserId(userId);
    }

    @Override
    public List<Permission> getPermissionListByRoleId(Long roleId) {
        return userMapper.getPermissionListByRoleId(roleId);
    }

    @Override
    public PageInfo getAllUsers() {
        PageHelper.startPage(2, 1);
        List<User> users = userMapper.getAllUsers();
        PageInfo pageInfo = new PageInfo(users);
        System.out.println("total "+pageInfo.getTotal());
        return pageInfo;
    }
}

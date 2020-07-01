package com.example.springboot.mapper;

import com.example.springboot.model.Permission;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;

import java.util.List;

public interface UserMapper {
    /**
     * 注册
     */
    public Integer save(User user);

    /**
     * 根据Id查找user
     */
    public User findUserById(Long userId);

    /**
     * 根据userid获取角色
     */
    public List<Role> getRoleListByUserId(Long userId);

    /**
     * 根据roleId获取权限
     */
    public List<Permission> getPermissionListByRoleId(Long roleId);

    /**
     * 获取用户根据登录名
     */
    public User getUserByLoginName(String loginName);

    /**
    * 分页获取所有分页
    * */
    public List<User> getAllUsers();
}

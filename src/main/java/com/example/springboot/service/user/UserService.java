package com.example.springboot.service.user;

import com.example.springboot.model.Permission;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    /**
     * 注册
     */
    public Integer register(User user);

    /**
     * 根据Id查找user
     */
    public User findUserById(Long userId);

    /**根据名称查找user
     *
     * */
    public  User getUserByLoginName(String loginName);

    /**
     * 根据userid获取角色
     */
    public List<Role> getRoleListByUserId(Long userId);

    /**
     * 根据roleId获取权限
     */
    public List<Permission> getPermissionListByRoleId(Long roleId);
    /**
     * 分页获取所有分页
     * */
    public PageInfo getAllUsers();
}

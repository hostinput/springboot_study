package com.example.springboot.system.service;

import com.example.springboot.system.entity.Permission;
import com.example.springboot.system.entity.Role;
import com.example.springboot.system.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
public interface IUserService extends IService<User> {
    /**
     * 根据userid获取角色
     */
    public List<Role> getRoleListByUserId(Long userId);

    /**
     * 根据roleId获取权限
     */
    public List<Permission> getPermissionListByRoleId(Long roleId);
}

package com.example.springboot.system.mapper;

import com.example.springboot.system.entity.Permission;
import com.example.springboot.system.entity.Role;
import com.example.springboot.system.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据roleId获取权限
     */
    List<Permission> selectPermissionListByRoleId(Long roleId);

    /**
     * 根据userid获取角色
     */
    List<Role> selectRoleListByUserId(Long userId);
}

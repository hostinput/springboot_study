package com.example.springboot.conf;

import com.baomidou.mybatisplus.mapper.Condition;
import com.example.springboot.system.entity.Permission;
import com.example.springboot.system.entity.Role;
import com.example.springboot.system.entity.User;
import com.example.springboot.system.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RealmConfig extends AuthorizingRealm {
    @Autowired
    IUserService userServiceImpl;

    /**
     * 用户验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String username = upt.getUsername();//是否是一样得效果待定：String userName = (String)token.getPrincipal();
        User usertoken = userServiceImpl.selectOne(new Condition().eq("username", username));
        if (usertoken == null) {
            throw new UnknownAccountException();
        }
        if (usertoken.getStatus() == 2) {//1有效; 2删除
            throw new DisabledAccountException();
        }
        //添加角色和权限
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                usertoken,
                usertoken.getPassword(), //密码
                usertoken.getUsername()// usertoken.getSalt()//salt=username+salt ByteSource.Util.bytes(bouy.getCredentialsSalt())
        );
        return authenticationInfo;

    }

    /**
     * 用户授权
     * 说明：采用set得目的是去重
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取认证时候添加到SimpleAuthenticationInfo中的实例
        User user = (User) principals.getPrimaryPrincipal();
        System.out.println(user.getId() + "认证成功进行授权中...");
        List<Role> roleList = userServiceImpl.getRoleListByUserId(Long.valueOf(user.getId()));//角色集合
        Set<String> roles = new HashSet<>(roleList.size());
        for (Role role : roleList) {
            String roleName = role.getName();
            System.out.println("role" + roleName);
            roles.add(roleName);
        }
        List<Permission> permissionList = userServiceImpl.getPermissionListByRoleId(Long.valueOf(user.getId()));//角色集合
        Set<String> permissions = new HashSet<>(permissionList.size());
        for (Permission permission : permissionList) {
            String permissionName = permission.getName();
            System.out.println("permissionName" + permissionName);
            permissions.add(permissionName);
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}

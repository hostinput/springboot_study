package com.example.springboot.system.web;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.example.springboot.aop.OperationType;
import com.example.springboot.aop.SystemLog;
import com.example.springboot.constants.Code;
import com.example.springboot.entity.BaseResult;
import com.example.springboot.system.entity.User;
import com.example.springboot.system.service.IUserService;
import com.example.springboot.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userServiceImpl;

    @RequestMapping("/register")
    @SystemLog(operationType = OperationType.INSERT, operationName = "用户注册")
    public BaseResult registerUser(String userName, String pwd) {
        if (StringUtils.isEmpty(userName)) {
            return ResultUtil.error(null, "用户名不能为空", Code.ERROR);
        }
        if (StringUtils.isEmpty(pwd)) {
            return ResultUtil.error(null, "密码不能为空", Code.ERROR);
        }
        int count = userServiceImpl.selectCount(new Condition().eq("username", userName));
        if (count > 0) {
            if (StringUtils.isEmpty(pwd)) {
                return ResultUtil.error(null, "用户名已存在", Code.ERROR);
            }
        }
        //用户注册
        User user = new User();
        user.setUsername(userName);
        user.setPassword(pwd);
        user.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        userServiceImpl.insert(user);
        return ResultUtil.ok(user);
    }

}

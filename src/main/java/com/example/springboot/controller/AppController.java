package com.example.springboot.controller;

import com.example.springboot.aop.OperationType;
import com.example.springboot.aop.SystemLog;
import com.example.springboot.entity.BaseResult;
import com.example.springboot.system.entity.User;
import com.example.springboot.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class AppController {
    // @Autowired
    //FirstService firstServiceImpl;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public BaseResult index() throws Exception {
        int x = 1 / 0;
        return ResultUtil.ok("hello spring boot and mybatis");
    }

    @SystemLog(operationType = OperationType.SELECT, operationName = "查找所有users")
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public BaseResult getUsers() {
        //查询所有user
        //   List<User> users = firstServiceImpl.getUsers();
        return ResultUtil.ok("success");
    }

    @SystemLog(operationType = OperationType.INSERT, operationName = "新增用户")
    @RequestMapping(value = "/adduser")
    public BaseResult addUser(@RequestBody User user) throws Exception {
        //int usernamex=1/0;
        // System.out.println(user.toString());
        // Integer userId = firstServiceImpl.addUser(user);
        return ResultUtil.ok(1);

    }

    @RequestMapping(value = "/getuserbyid")
    public BaseResult getUserById(Integer userId) {
        System.out.println("userId" + userId);
        //  User user = firstServiceImpl.getUserById(userId);
        return ResultUtil.ok("success");

    }


}

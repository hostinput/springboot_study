package com.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class AppController {
    // @Autowired
    //FirstService firstServiceImpl;
/*
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResultBody index() throws Exception {
        int x = 1 / 0;
        return ResultUtil.ok("hello spring boot and mybatis");
    }

    @SystemLog(operationType = OperationType.SELECT, operationName = "查找所有users")
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public ResultBody getUsers() {
        //查询所有user
        //   List<User> users = firstServiceImpl.getUsers();
        return ResultUtil.ok("success");
    }

    @SystemLog(operationType = OperationType.INSERT, operationName = "新增用户")
    @RequestMapping(value = "/adduser")
    public ResultBody addUser(@RequestBody User user) throws Exception {
        //int usernamex=1/0;
        // System.out.println(user.toString());
        // Integer userId = firstServiceImpl.addUser(user);
        return ResultUtil.ok(1);

    }

    @RequestMapping(value = "/getuserbyid")
    public ResultBody getUserById(Integer userId) {
        System.out.println("userId" + userId);
        //  User user = firstServiceImpl.getUserById(userId);
        return ResultUtil.ok("success");

    }
*/


}

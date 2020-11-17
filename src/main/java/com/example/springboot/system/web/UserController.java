package com.example.springboot.system.web;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.example.springboot.aop.OperationType;
import com.example.springboot.aop.SystemLog;
import com.example.springboot.system.entity.User;
import com.example.springboot.system.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xubo
 * @since 2020-07-02
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService userServiceImpl;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toIndex")
    public String toIndex(Model model) {
        logger.info("测试打印Info");
        logger.error("测试打印error");
        List<User> userList = userServiceImpl.selectList(null);
        model.addAttribute("userList", userList);
        return "index";
    }
    @RequestMapping("/toNouth")
    public String toNouth(Model model) {
        return "noauth";
    }
    @RequestMapping("/toError")
    public String toError(Model model) {
        return "my_error";
    }

    @RequestMapping("/toUpdata/{userId}")
    public String toUpdata(Model model, @PathVariable Integer userId) {
        User user = userServiceImpl.selectById(userId);
        model.addAttribute("user", user);
        return "updata";
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/updata")
    @SystemLog(operationType = OperationType.UPDATE, operationName = "修改用户信息")
    @RequiresPermissions("user:updata")
    public String updata(RedirectAttributes attributes, String userName, Integer age, String email, String phone, Integer sex, Integer userId) {
        try {
            User user = userServiceImpl.selectById(userId);
            if (!StringUtils.isEmpty(userName)) {
                user.setUsername(userName);
            }
            if (!StringUtils.isEmpty(phone)) {
                user.setPhone(phone);
            }
            if (!StringUtils.isEmpty(email)) {
                user.setEmail(email);
            }
            if (age != null && age > 0) {
                user.setAge(age);
            }
            if (sex != null && sex > 0) {
                user.setSex(sex);
            }
            userServiceImpl.updateById(user);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "更新失败");
            return "redirect:/user/toUpdata/" + userId;
        }


        return "redirect:/user/toIndex";
    }

    /**
     * 禁用或者启用用户
     */
    @RequestMapping("/changeStatus/{status}/{userId}")
    @SystemLog(operationType = OperationType.UPDATE, operationName = "修改用户状态")
    public String changeStatus(@PathVariable Integer userId, @PathVariable Integer status) {
        User user = userServiceImpl.selectById(userId);
        if (user != null) {
            user.setStatus(status);
            userServiceImpl.insertOrUpdate(user);
        }
        return "redirect:/user/toIndex";
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public String Login(String username, String pwd, RedirectAttributes attributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            // 重定向，传参
            attributes.addFlashAttribute("msg", "用户不存在");
            return "redirect:/user/toLogin";
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户名和密码不匹配");
            attributes.addFlashAttribute("msg", "用户名和密码不匹配");
            return "redirect:/user/toLogin";
        } catch (LockedAccountException lae) {
            attributes.addFlashAttribute("msg", "用户已锁定，请联系管理员");
            return "redirect:/user/toLogin";
        } catch (DisabledAccountException eae) {
            attributes.addFlashAttribute("msg", "账号被禁用");
            return "redirect:/user/toLogin";
        } catch (AuthenticationException ae) {
            System.out.println("AuthenticationException");
            attributes.addFlashAttribute("msg", "AuthenticationException");
            return "redirect:/user/toLogin";
        }
        return "redirect:/user/toIndex";
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public String registerUser(RedirectAttributes attributes, String userName, String pwd, Integer age, String email, String phone, Integer sex) {
        if (StringUtils.isEmpty(userName)) {
            attributes.addFlashAttribute("error", "用户名不能为空");
            return "redirect:/user/toRegister";
        }
        if (StringUtils.isEmpty(pwd)) {
            attributes.addFlashAttribute("error", "密码不能为空");
            return "redirect:/user/toRegister";
        }
        int count = userServiceImpl.selectCount(new Condition().eq("username", userName));
        if (count > 0) {
            attributes.addFlashAttribute("error", "用户已存在");
            return "redirect:/user/toRegister";
        }
        try {
            User user = new User();
            user.setUsername(userName);
            user.setSalt(userName);
            user.setPassword(pwd);
            user.setCreateTime(new Date());
            user.setAge(age);
            user.setEmail(email);
            user.setPhone(phone);
            user.setSex(sex);
            user.setStatus(1);
            userServiceImpl.insert(user);
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "注册失败");
            return "redirect:/user/toRegister";
        }
        return "redirect:/user/toLogin";
    }

}

package com.example.springboot.controller;

import com.example.springboot.aop.OperationType;
import com.example.springboot.aop.SystemLog;
import com.example.springboot.constants.Code;
import com.example.springboot.entity.BaseResult;
import com.example.springboot.model.User;
import com.example.springboot.service.user.UserService;
import com.example.springboot.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    UserService userService;
    @SystemLog(operationType = OperationType.SELECT, operationName = "hello spring boot")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @RequiresPermissions("view:del")
    public String hello(Model model) {
        // int x=1/0;
        model.addAttribute("content", "hello world spring boot");
        model.addAttribute("value", 10);
        System.out.println("hello");
        return "hello";
    }

    @RequestMapping("/login")
    public String login(Model model, @ModelAttribute("msg") String msg) {//重定向接受参数
        model.addAttribute(msg);
        return "login";
    }

    @RequestMapping("/userlogin")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String pwd, RedirectAttributes attributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            //boolean isAddPer = currentUser.isPermitted("view:del");
            // System.out.println("是否用户view:add权限"+isAddPer);
        } catch (UnknownAccountException uae) {
            // 重定向，传参
            System.out.println("用户不存在");
            attributes.addFlashAttribute("msg", "用户不存在");
            return "redirect:/view/login";
        } catch (IncorrectCredentialsException ice) {
            System.out.println("用户名和密码不匹配");
            attributes.addFlashAttribute("msg", "用户名和密码不匹配");
            return "redirect:/view/login";
        } catch (LockedAccountException lae) {
            System.out.println("LockedAccountException");
            attributes.addFlashAttribute("msg", "LockedAccountException");
            return "redirect:/view/login";
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("ExcessiveAttemptsException");
            attributes.addFlashAttribute("msg", "ExcessiveAttemptsException");
            return "redirect:/view/login";
        } catch (AuthenticationException ae) {
            System.out.println("AuthenticationException");
            attributes.addFlashAttribute("msg", "AuthenticationException");
            return "redirect:/view/login";
        }
        return "redirect:/view/index";
    }
    /**
     * 登录成功后跳转界面
     * */
    @RequestMapping("/index")
    @RequiresPermissions(value = "view:add")
    public String index() {
        return "index";
    }
    /**
     * 未授权跳转界面
     * */
    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noauth";
    }
    /**
    * 展示所有users
    * */
    @RequestMapping("/listusers")
    @ResponseBody
    public BaseResult getAllUser(){
        PageInfo userInfo = userService.getAllUsers();
       // System.out.println("users.size"+users.size());
        return  ResultUtil.ok(userInfo);
    }
}

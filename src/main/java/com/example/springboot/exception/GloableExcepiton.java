package com.example.springboot.exception;

import com.example.springboot.constants.Code;
import com.example.springboot.entity.BaseResult;
import com.example.springboot.utils.ResultUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获
 */
@ControllerAdvice
public class GloableExcepiton extends RuntimeException {
    public static final String ERROR_VIEW = "my_error";

    @ExceptionHandler(Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
             e.printStackTrace();
        if (isAjax(request)) {//是ajax请求
            if (e instanceof UnauthorizedException) {//无权限异常捕获，跳转到无权限页面
                return ResultUtil.error(null, "无权限", Code.ERROR);
            } else {
                return ResultUtil.error(null, e.getMessage(), Code.ERROR);
            }
        } else {//不是ajax请求,跳转页面
            ModelAndView modelAndView = new ModelAndView();
            if (e instanceof UnauthorizedException) {//无权限异常捕获，跳转到无权限页面
                modelAndView.setViewName("noauth");
            } else {
                ModelAndView mv = new ModelAndView();
                mv.addObject("exception", e);
                mv.addObject("url", request.getRequestURL());//发生异常的路径
                mv.setViewName(ERROR_VIEW);//指定发生异常之后跳转页面
            }
            return modelAndView;
        }

    }

    private boolean isAjax(HttpServletRequest request) {//判断request是否是ajax请求
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}

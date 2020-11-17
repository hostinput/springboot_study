package com.example.springboot.exception;

import com.example.springboot.constants.Code;
import com.example.springboot.entity.ResultBody;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获
 */
@ControllerAdvice
public class GloableExcepiton extends RuntimeException {
    public static final String ERROR_VIEW = "my_error";
    private static final Logger logger = LoggerFactory.getLogger(GloableExcepiton.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }


/*    *//**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     *//*
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(Exception.class)
    public String errorHandler(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes, Exception e) {
        e.printStackTrace();
  /*      if (isAjax(request)) {//是ajax请求
            if (e instanceof UnauthorizedException) {//无权限异常捕获，跳转到无权限页面
                return ResultUtil.error(null, "无权限", Code.ERROR);
            } else {
                return ResultUtil.error(null, e.getMessage(), Code.ERROR);
            }
        } else {//不是ajax请求,跳转页面*/
            if (e instanceof UnauthorizedException) {//无权限异常捕获，跳转到无权限页面
                return "redirect:/user/toNouth";
            } else {//其他错误，跳转到错误页面
                attributes.addFlashAttribute("exception", e);
                attributes.addFlashAttribute("url", request.getRequestURL());
                return "redirect:/user/toError";
          //  }
        }


    }
    private boolean isAjax(HttpServletRequest request) { //判断request是否是ajax请求
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}

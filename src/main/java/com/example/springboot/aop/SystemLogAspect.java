package com.example.springboot.aop;

import com.google.gson.Gson;
import org.apache.catalina.security.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Objects;

@Component
@Aspect
public class SystemLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.example.springboot.aop.SystemLog)")
    public void sysLogAspect() {

    }

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        try{
            //1.获取到所有的参数值的数组
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //2.获取到方法的所有参数名称的字符串数组
            String[] parameterNames = methodSignature.getParameterNames();
            Method method = methodSignature.getMethod();
            System.out.println("---------------参数列表开始-------------------------");
            for (int i =0 ,len=parameterNames.length;i < len ;i++){
                System.out.println("参数名："+ parameterNames[i] + " = " +args[i]);
            }
            // 3.获取当前请求的httpRequest，可获取session与其他参数
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session =request.getSession();
            System.out.println("---------------参数列表结束-------------------------");
            SystemLog redis=(SystemLog)method.getAnnotation(SystemLog.class);
            System.out.println("自定义注解 operationName:" + redis.operationName());
            System.out.println("自定义注解 operationType:" + redis.operationType());
            //保存数据库
        }catch (Exception e){
            e.printStackTrace();
        }

        // 开始时间
       /* long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        PreUser securityUser = SecurityUtil.getUser();
        sysLog.setUserName(securityUser.getUsername());
        sysLog.setActionUrl(URLUtil.getPath(request.getRequestURI()));
        sysLog.setStartTime(LocalDateTime.now());
        sysLog.setRequestIp(ServletUtil.getClientIP(request));
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setUa(request.getHeader("user-agent"));
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        //获取执行的方法名
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        // 类名
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        sysLog.setFinishTime(LocalDateTime.now());
        // 参数
        sysLog.setParams(Arrays.toString(args));
        sysLog.setDescription(LogUtil.getControllerMethodDescription(joinPoint));
        long endTime = Instant.now().toEpochMilli();
        sysLog.setConsumingTime(endTime - beginTime);*/
    }

    /**
     * 返回通知
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
     /*   R r = Convert.convert(R.class, ret);
        if (r.getCode() == 200){
            // 正常返回
            sysLog.setType(1);
        } else {
            sysLog.setType(2);
            sysLog.setExDetail(r.getMsg());
        }
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));*/
    }

    /**
     * 异常通知
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()",throwing = "e")
    public void doAfterThrowable(Throwable e){
        // 异常
        /*sysLog.setType(2);
        // 异常对象
        sysLog.setExDetail(LogUtil.getStackTrace(e));
        // 异常信息
        sysLog.setExDesc(e.getMessage());
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));*/
    }


}

package com.example.springboot.aop;

import org.omg.CORBA.UNKNOWN;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 要执行的操作类型比如：add操作
     **/
    public OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 要执行的具体操作比如：添加用户
     **/
    public String operationName() default "";
}

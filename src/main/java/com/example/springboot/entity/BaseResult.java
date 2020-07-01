package com.example.springboot.entity;

import com.example.springboot.constants.Code;

public class BaseResult<T> {
    public Code code;
    public String msg;
    public T data;

    public BaseResult() {
    }

    public BaseResult(String msg, T data, Code code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }
    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
